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
package com.odex.apps.account.service.moveline.massentry;

import com.odex.apps.account.db.Account;
import com.odex.apps.account.db.Move;
import com.odex.apps.account.db.MoveLineMassEntry;
import com.odex.apps.account.db.repo.MoveLineMassEntryRepository;
import com.odex.apps.account.db.repo.MoveRepository;
import com.odex.apps.account.service.app.AppAccountService;
import com.odex.apps.account.service.invoice.InvoiceTermPfpToolService;
import com.odex.apps.account.service.move.MoveCounterPartService;
import com.odex.apps.account.service.move.massentry.MassEntryToolService;
import com.odex.apps.account.service.moveline.MoveLineTaxService;
import com.odex.apps.base.AxelorException;
import com.odex.apps.base.db.Company;
import com.odex.apps.base.db.Currency;
import com.odex.apps.base.db.Partner;
import com.odex.apps.base.service.CurrencyService;
import com.odex.apps.base.service.app.AppBaseService;
import com.axelor.auth.db.User;
import com.axelor.common.ObjectUtils;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class MoveLineMassEntryServiceImpl implements MoveLineMassEntryService {

  protected MoveLineTaxService moveLineTaxService;
  protected MoveCounterPartService moveCounterPartService;
  protected MassEntryToolService massEntryToolService;
  protected CurrencyService currencyService;
  protected AppAccountService appAccountService;
  protected AppBaseService appBaseService;
  protected InvoiceTermPfpToolService invoiceTermPfpToolService;

  @Inject
  public MoveLineMassEntryServiceImpl(
      MoveLineTaxService moveLineTaxService,
      MoveCounterPartService moveCounterPartService,
      MassEntryToolService massEntryToolService,
      CurrencyService currencyService,
      AppAccountService appAccountService,
      AppBaseService appBaseService,
      InvoiceTermPfpToolService invoiceTermPfpToolService) {
    this.moveLineTaxService = moveLineTaxService;
    this.moveCounterPartService = moveCounterPartService;
    this.massEntryToolService = massEntryToolService;
    this.currencyService = currencyService;
    this.appAccountService = appAccountService;
    this.appBaseService = appBaseService;
    this.invoiceTermPfpToolService = invoiceTermPfpToolService;
  }

  @Override
  public void generateTaxLineAndCounterpart(
      Move parentMove, Move childMove, LocalDate dueDate, Integer temporaryMoveNumber)
      throws AxelorException {
    if (ObjectUtils.notEmpty(childMove.getMoveLineList())) {
      if (childMove.getStatusSelect().equals(MoveRepository.STATUS_NEW)
          || childMove.getStatusSelect().equals(MoveRepository.STATUS_SIMULATED)) {
        moveLineTaxService.autoTaxLineGenerateNoSave(childMove);
        moveCounterPartService.generateCounterpartMoveLine(childMove, dueDate);
      }
      massEntryToolService.clearMoveLineMassEntryListAndAddNewLines(
          parentMove, childMove, temporaryMoveNumber);
    }
  }

  @Override
  public BigDecimal computeCurrentRate(
      BigDecimal currencyRate,
      Company company,
      List<MoveLineMassEntry> moveLineList,
      Currency currency,
      Currency companyCurrency,
      Integer temporaryMoveNumber,
      LocalDate originDate)
      throws AxelorException {
    if (currency != null && companyCurrency != null && !currency.equals(companyCurrency)) {
      if (moveLineList.size() == 0) {
        if (originDate != null) {
          currencyRate =
              currencyService.getCurrencyConversionRate(currency, companyCurrency, originDate);
        } else {
          currencyRate =
              currencyService.getCurrencyConversionRate(
                  currency, companyCurrency, appBaseService.getTodayDate(company));
        }
      } else {
        if (moveLineList.stream()
            .anyMatch(it -> Objects.equals(it.getTemporaryMoveNumber(), temporaryMoveNumber))) {
          currencyRate =
              moveLineList.stream()
                  .filter(it -> Objects.equals(it.getTemporaryMoveNumber(), temporaryMoveNumber))
                  .findFirst()
                  .get()
                  .getCurrencyRate();
        }
      }
    }
    return currencyRate;
  }

  @Override
  public User getPfpValidatorUserForInTaxAccount(
      Account account, Company company, Partner partner) {
    if (ObjectUtils.notEmpty(account) && account.getUseForPartnerBalance()) {
      return invoiceTermPfpToolService.getPfpValidatorUser(partner, company);
    }
    return null;
  }

  @Override
  public void setPfpValidatorUserForInTaxAccount(
      List<MoveLineMassEntry> moveLineMassEntryList, Company company, int temporaryMoveNumber) {
    for (MoveLineMassEntry moveLine : moveLineMassEntryList) {
      if (moveLine.getTemporaryMoveNumber() == temporaryMoveNumber
          && ObjectUtils.isEmpty(moveLine.getMovePfpValidatorUser())) {
        moveLine.setMovePfpValidatorUser(
            getPfpValidatorUserForInTaxAccount(
                moveLine.getAccount(), company, moveLine.getPartner()));
      }
    }
  }

  protected void setDefaultValues(MoveLineMassEntry moveLine, Company company) {
    LocalDate todayDate = appBaseService.getTodayDate(company);

    moveLine.setTemporaryMoveNumber(1);
    moveLine.setCounter(1);
    moveLine.setDate(todayDate);
    moveLine.setOriginDate(todayDate);
    moveLine.setCurrencyRate(BigDecimal.ONE);
    moveLine.setIsEdited(MoveLineMassEntryRepository.MASS_ENTRY_IS_EDITED_NULL);
    moveLine.setInputAction(MoveLineMassEntryRepository.MASS_ENTRY_INPUT_ACTION_LINE);

    if (appAccountService.getAppAccount().getManageCutOffPeriod()) {
      moveLine.setCutOffStartDate(todayDate);
      moveLine.setCutOffEndDate(todayDate);
      moveLine.setDeliveryDate(todayDate);
    }
  }

  @Override
  public MoveLineMassEntry createMoveLineMassEntry(Company company) {
    MoveLineMassEntry newMoveLine = new MoveLineMassEntry();
    setDefaultValues(newMoveLine, company);

    return newMoveLine;
  }
}
