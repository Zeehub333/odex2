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

import com.odex.apps.account.db.Journal;
import com.odex.apps.account.db.Move;
import com.odex.apps.account.db.MoveLine;
import com.odex.apps.base.AxelorException;
import com.odex.apps.base.db.Company;
import java.util.Map;

public interface MoveLineAttrsService {

  void addDescriptionRequired(Move move, Map<String, Map<String, Object>> attrsMap)
      throws AxelorException;

  void addAnalyticAccountRequired(
      MoveLine moveLine, Move move, Map<String, Map<String, Object>> attrsMap)
      throws AxelorException;

  void addAnalyticDistributionTypeSelect(Move move, Map<String, Map<String, Object>> attrsMap)
      throws AxelorException;

  void addInvoiceTermListPercentageWarningText(
      MoveLine moveLine, Map<String, Map<String, Object>> attrsMap);

  void addReadonly(MoveLine moveLine, Move move, Map<String, Map<String, Object>> attrsMap);

  void addShowTaxAmount(MoveLine moveLine, Map<String, Map<String, Object>> attrsMap);

  void addShowAnalyticDistributionPanel(
      Move move, MoveLine moveLine, Map<String, Map<String, Object>> attrsMap)
      throws AxelorException;

  void addValidatePeriod(Move move, Map<String, Map<String, Object>> attrsMap)
      throws AxelorException;

  void addPartnerReadonly(MoveLine moveLine, Move move, Map<String, Map<String, Object>> attrsMap);

  void addAccountDomain(
      Journal journal, Company company, Map<String, Map<String, Object>> attrsMap);

  void addPartnerDomain(Move move, Map<String, Map<String, Object>> attrsMap);

  void addAnalyticDistributionTemplateDomain(
      Move move, MoveLine moveLine, Map<String, Map<String, Object>> attrsMap)
      throws AxelorException;

  void changeFocus(Move move, MoveLine moveLine, Map<String, Map<String, Object>> attrsMap);

  void addThirdPartyPayerPartnerHidden(Move move, Map<String, Map<String, Object>> attrsMap);

  void addTaxLineRequired(Move move, MoveLine moveLine, Map<String, Map<String, Object>> attrsMap);

  void addCutOffPanelHidden(
      Move move, MoveLine moveLine, Map<String, Map<String, Object>> attrsMap);

  void addCutOffDatesRequired(
      Move move, MoveLine moveLine, Map<String, Map<String, Object>> attrsMap);

  void addVatSystemSelectReadonly(
      Move move, MoveLine moveLine, Map<String, Map<String, Object>> attrsMap);
}
