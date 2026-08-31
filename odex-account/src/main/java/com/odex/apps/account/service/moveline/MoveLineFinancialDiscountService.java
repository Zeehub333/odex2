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
package com.odex.apps.account.service.moveline;

import com.odex.apps.account.db.Account;
import com.odex.apps.account.db.Invoice;
import com.odex.apps.account.db.InvoicePayment;
import com.odex.apps.account.db.Move;
import com.odex.apps.account.db.MoveLine;
import com.odex.apps.base.AxelorException;
import com.odex.apps.base.db.Company;
import com.odex.apps.base.db.Partner;
import com.axelor.meta.CallMethod;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;

public interface MoveLineFinancialDiscountService {
  @CallMethod
  LocalDate getFinancialDiscountDeadlineDate(MoveLine moveLine);

  void computeFinancialDiscount(MoveLine moveLine, Move move);

  boolean isFinancialDiscountLine(MoveLine moveLine, Company company, boolean isPurchase)
      throws AxelorException;

  int createFinancialDiscountMoveLine(
      Invoice invoice,
      Move move,
      InvoicePayment invoicePayment,
      String origin,
      int counter,
      boolean isDebit,
      boolean financialDiscountVat)
      throws AxelorException;

  int createFinancialDiscountMoveLine(
      Move move,
      Partner partner,
      Map<String, Pair<BigDecimal, BigDecimal>> taxMap,
      Map<String, Integer> vatSystemTaxMap,
      Map<String, Account> accountTaxMap,
      Account financialDiscountAccount,
      String origin,
      String description,
      BigDecimal financialDiscountAmount,
      BigDecimal financialDiscountTaxAmount,
      LocalDate paymentDate,
      int counter,
      boolean isDebit,
      boolean financialDiscountVat)
      throws AxelorException;

  Map<String, Account> getAccountTaxMap(Move move);

  Map<String, Integer> getVatSystemTaxMap(Move move);

  Map<String, Pair<BigDecimal, BigDecimal>> getFinancialDiscountTaxMap(MoveLine moveLine);
}
