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
package com.odex.apps.account.service.umr;

import com.odex.apps.account.db.InvoicingPaymentSituation;
import com.odex.apps.account.db.Umr;
import com.odex.apps.account.db.repo.InvoicingPaymentSituationRepository;
import com.odex.apps.base.AxelorException;
import com.odex.apps.base.db.BankDetails;
import com.odex.apps.base.db.Company;
import com.odex.apps.base.db.Partner;
import com.odex.apps.base.service.PartnerService;
import com.odex.apps.base.service.app.AppBaseService;
import com.axelor.common.ObjectUtils;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UmrServiceImpl implements UmrService {

  protected AppBaseService appBaseService;
  protected PartnerService partnerService;
  protected UmrNumberService umrNumberService;
  protected InvoicingPaymentSituationRepository invoicingPaymentSituationRepository;

  @Inject
  public UmrServiceImpl(
      AppBaseService appBaseService,
      PartnerService partnerService,
      UmrNumberService umrNumberService,
      InvoicingPaymentSituationRepository invoicingPaymentSituationRepository) {
    this.appBaseService = appBaseService;
    this.partnerService = partnerService;
    this.umrNumberService = umrNumberService;
    this.invoicingPaymentSituationRepository = invoicingPaymentSituationRepository;
  }

  @Override
  public Map<String, Object> getOnNewValuesMap(InvoicingPaymentSituation invoicingPaymentSituation)
      throws AxelorException {
    Map<String, Object> valuesMap = new HashMap<>();

    LocalDate date = appBaseService.getTodayDate(invoicingPaymentSituation.getCompany());

    valuesMap.put("creationDate", date);
    valuesMap.put("mandateSignatureDate", date);

    if (invoicingPaymentSituation.getPartner() != null) {
      Partner partner = invoicingPaymentSituation.getPartner();
      valuesMap.put("debtorName", partner.getName());
      valuesMap.put("debtorAddress", partnerService.getInvoicingAddress(partner));
    }
    valuesMap.put("umrNumber", umrNumberService.getUmrNumber(invoicingPaymentSituation, date));

    return valuesMap;
  }

  @Override
  public Umr getActiveUmr(Company company, BankDetails bankDetails) {
    if (company == null || bankDetails == null) {
      return null;
    }

    InvoicingPaymentSituation invoicingPaymentSituation =
        invoicingPaymentSituationRepository.findByCompanyAndBankDetails(company, bankDetails);
    if (invoicingPaymentSituation != null) {
      if (invoicingPaymentSituation.getActiveUmr() != null) {
        return invoicingPaymentSituation.getActiveUmr();
      }
      List<Umr> umrList = invoicingPaymentSituation.getUmrList();
      if (!ObjectUtils.isEmpty(umrList) && umrList.size() == 1) {
        return umrList.get(0);
      }
    }
    return null;
  }
}
