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
package com.odex.apps.account.service.notebills;

import com.odex.apps.account.db.AccountingBatch;
import com.odex.apps.account.db.NoteBills;
import com.odex.apps.account.db.repo.NoteBillsRepository;
import com.odex.apps.account.exception.AccountExceptionMessage;
import com.odex.apps.base.AxelorException;
import com.odex.apps.base.db.Batch;
import com.odex.apps.base.db.Company;
import com.odex.apps.base.db.Partner;
import com.odex.apps.base.db.repo.SequenceRepository;
import com.odex.apps.base.db.repo.TraceBackRepository;
import com.odex.apps.base.exceptions.BaseExceptionMessage;
import com.odex.apps.base.service.administration.SequenceService;
import com.axelor.i18n.I18n;
import com.google.inject.persist.Transactional;
import jakarta.inject.Inject;
import java.util.Objects;

public class NoteBillsCreateServiceImpl implements NoteBillsCreateService {

  protected NoteBillsRepository noteBillsRepository;
  protected SequenceService sequenceService;

  @Inject
  public NoteBillsCreateServiceImpl(
      NoteBillsRepository noteBillsRepository, SequenceService sequenceService) {
    this.noteBillsRepository = noteBillsRepository;
    this.sequenceService = sequenceService;
  }

  @Override
  @Transactional(rollbackOn = {Exception.class})
  public NoteBills createNoteBills(Company company, Partner partner, Batch batch)
      throws AxelorException {
    Objects.requireNonNull(company);
    Objects.requireNonNull(partner);
    Objects.requireNonNull(batch);

    NoteBills noteBills = new NoteBills();

    noteBills.setCompany(company);
    noteBills.setPartner(partner);
    noteBills.setBatch(batch);
    noteBills.setEmailAddress(partner.getEmailAddress());
    AccountingBatch accountingBatch = batch.getAccountingBatch();
    if (accountingBatch != null) {
      noteBills.setDueDate(accountingBatch.getDueDate());
      noteBills.setBillOfExchangeTypeSelect(accountingBatch.getBillOfExchangeTypeSelect());
    }
    noteBills.setNoteBillsSeq(generateSequence(noteBills));

    return noteBillsRepository.save(noteBills);
  }

  public String generateSequence(NoteBills noteBills) throws AxelorException {

    if (!sequenceService.hasSequence(SequenceRepository.NOTE_BILLS, noteBills.getCompany())) {
      throw new AxelorException(
          TraceBackRepository.CATEGORY_CONFIGURATION_ERROR,
          I18n.get(AccountExceptionMessage.NOTE_BILLS_CONFIG_SEQUENCE),
          I18n.get(BaseExceptionMessage.EXCEPTION),
          noteBills.getCompany().getName());
    }
    String seq =
        sequenceService.getSequenceNumber(
            SequenceRepository.NOTE_BILLS,
            noteBills.getCompany(),
            NoteBills.class,
            "noteBillsSeq",
            noteBills);
    return seq;
  }
}
