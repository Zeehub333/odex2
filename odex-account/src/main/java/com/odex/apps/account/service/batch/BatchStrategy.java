/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2005-2026 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.odex.apps.account.service.batch;

import com.odex.apps.account.db.Account;
import com.odex.apps.account.db.AccountingBatch;
import com.odex.apps.account.db.AccountingReport;
import com.odex.apps.account.db.AccountingSituation;
import com.odex.apps.account.db.Invoice;
import com.odex.apps.account.db.Move;
import com.odex.apps.account.db.PaymentScheduleLine;
import com.odex.apps.account.db.PaymentVoucher;
import com.odex.apps.account.db.Reimbursement;
import com.odex.apps.account.db.repo.MoveLineRepository;
import com.odex.apps.account.db.repo.MoveRepository;
import com.odex.apps.account.exception.AccountExceptionMessage;
import com.odex.apps.account.service.AccountCustomerService;
import com.odex.apps.account.service.MoveLineExportService;
import com.odex.apps.account.service.ReimbursementExportService;
import com.odex.apps.account.service.ReimbursementImportService;
import com.odex.apps.account.service.ReimbursementService;
import com.odex.apps.account.service.RejectImportService;
import com.odex.apps.account.service.bankorder.file.cfonb.CfonbExportService;
import com.odex.apps.account.service.bankorder.file.cfonb.CfonbImportService;
import com.odex.apps.account.service.debtrecovery.DebtRecoveryService;
import com.odex.apps.account.service.debtrecovery.DoubtfulCustomerService;
import com.odex.apps.account.service.moveline.MoveLineService;
import com.odex.apps.account.service.payment.PaymentModeService;
import com.odex.apps.base.AxelorException;
import com.odex.apps.base.db.Partner;
import com.odex.apps.base.db.repo.BatchRepository;
import com.odex.apps.base.db.repo.CompanyRepository;
import com.odex.apps.base.db.repo.TraceBackRepository;
import com.odex.apps.base.exceptions.BaseExceptionMessage;
import com.odex.apps.base.service.administration.AbstractBatch;
import com.axelor.i18n.I18n;
import jakarta.inject.Inject;

public abstract class BatchStrategy extends AbstractBatch {

  protected DebtRecoveryService debtRecoveryService;
  protected DoubtfulCustomerService doubtfulCustomerService;
  protected ReimbursementExportService reimbursementExportService;
  protected ReimbursementImportService reimbursementImportService;
  protected RejectImportService rejectImportService;
  protected CfonbExportService cfonbExportService;
  protected CfonbImportService cfonbImportService;
  protected PaymentModeService paymentModeService;
  protected AccountCustomerService accountCustomerService;
  protected MoveLineExportService moveLineExportService;
  protected BatchAccountCustomer batchAccountCustomer;

  @Inject protected BatchRepository batchRepo;

  @Inject protected CompanyRepository companyRepo;

  @Inject protected MoveRepository moveRepo;

  @Inject protected MoveLineService moveLineService;

  @Inject protected MoveLineRepository moveLineRepo;

  @Inject protected ReimbursementService reimbursementService;

  protected BatchStrategy() {}

  protected BatchStrategy(DebtRecoveryService debtRecoveryService) {
    super();
    this.debtRecoveryService = debtRecoveryService;
  }

  protected BatchStrategy(
      DoubtfulCustomerService doubtfulCustomerService, BatchAccountCustomer batchAccountCustomer) {
    super();
    this.doubtfulCustomerService = doubtfulCustomerService;
    this.batchAccountCustomer = batchAccountCustomer;
  }

  protected BatchStrategy(
      ReimbursementExportService reimbursementExportService,
      CfonbExportService cfonbExportService,
      BatchAccountCustomer batchAccountCustomer) {
    super();
    this.reimbursementExportService = reimbursementExportService;
    this.cfonbExportService = cfonbExportService;
    this.batchAccountCustomer = batchAccountCustomer;
  }

  protected BatchStrategy(
      ReimbursementImportService reimbursementImportService,
      RejectImportService rejectImportService,
      BatchAccountCustomer batchAccountCustomer) {
    super();
    this.reimbursementImportService = reimbursementImportService;
    this.rejectImportService = rejectImportService;
    this.batchAccountCustomer = batchAccountCustomer;
  }

  protected BatchStrategy(AccountCustomerService accountCustomerService) {
    super();
    this.accountCustomerService = accountCustomerService;
  }

  protected BatchStrategy(MoveLineExportService moveLineExportService) {
    super();
    this.moveLineExportService = moveLineExportService;
  }

  protected void updateInvoice(Invoice invoice) {

    invoice.addBatchSetItem(batchRepo.find(batch.getId()));

    incrementDone();
  }

  protected void updateReimbursement(Reimbursement reimbursement) {

    reimbursement.addBatchSetItem(batchRepo.find(batch.getId()));

    incrementDone();
  }

  protected void updatePaymentScheduleLine(PaymentScheduleLine paymentScheduleLine) {

    paymentScheduleLine.addBatchSetItem(batchRepo.find(batch.getId()));

    incrementDone();
  }

  protected void updatePaymentVoucher(PaymentVoucher paymentVoucher) {

    paymentVoucher.addBatchSetItem(batchRepo.find(batch.getId()));

    incrementDone();
  }

  protected void updatePartner(Partner partner) {

    partner.addBatchSetItem(batchRepo.find(batch.getId()));

    incrementDone();
  }

  protected void updateAccountingSituation(AccountingSituation accountingSituation) {

    accountingSituation.addBatchSetItem(batchRepo.find(batch.getId()));

    incrementDone();
  }

  protected void updateAccountingReport(AccountingReport accountingReport) {

    accountingReport.addBatchSetItem(batchRepo.find(batch.getId()));

    incrementDone();
  }

  protected void updateAccount(Account account) {

    account.addBatchSetItem(batchRepo.find(batch.getId()));

    incrementDone();
  }

  protected void updateAccountMove(Move move, boolean incrementDone) {

    move.addBatchSetItem(batchRepo.find(batch.getId()));

    if (incrementDone) {
      incrementDone();
    } else {
      checkPoint();
    }
  }

  public void testAccountingBatchBankDetails(AccountingBatch accountingBatch)
      throws AxelorException {

    if (accountingBatch.getBankDetails() == null) {
      throw new AxelorException(
          accountingBatch,
          TraceBackRepository.CATEGORY_CONFIGURATION_ERROR,
          I18n.get(AccountExceptionMessage.BATCH_STRATEGY_1),
          I18n.get(BaseExceptionMessage.EXCEPTION),
          accountingBatch.getCode());
    }

    this.cfonbExportService.testBankDetailsField(accountingBatch.getBankDetails());
  }

  @Override
  protected void setBatchTypeSelect() {
    this.batch.setBatchTypeSelect(BatchRepository.BATCH_TYPE_ACCOUNTING_BATCH);
  }

  @Override
  protected Integer getFetchLimit() {
    Integer batchFetchLimit = this.batch.getAccountingBatch().getFetchLimit();
    if (batchFetchLimit == 0) {
      batchFetchLimit = super.getFetchLimit();
    }
    return batchFetchLimit;
  }
}
