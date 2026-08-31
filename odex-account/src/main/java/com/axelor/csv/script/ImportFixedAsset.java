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
package com.axelor.csv.script;

import com.odex.apps.account.db.FixedAsset;
import com.odex.apps.account.db.repo.FixedAssetRepository;
import com.odex.apps.account.service.fixedasset.FixedAssetGenerationService;
import com.odex.apps.base.AxelorException;
import jakarta.inject.Inject;
import java.util.Map;

public class ImportFixedAsset {

  @Inject FixedAssetGenerationService fixedAssetGenerationService;

  public Object importFixedAsset(Object bean, Map<String, Object> values) throws AxelorException {
    assert bean instanceof FixedAsset;
    FixedAsset fixedAsset = (FixedAsset) bean;
    if (fixedAsset != null
        && (fixedAsset.getOriginSelect() == null || fixedAsset.getOriginSelect() == 0)) {
      fixedAsset.setOriginSelect(FixedAssetRepository.ORIGINAL_SELECT_IMPORT);
    }
    fixedAssetGenerationService.generateAndComputeLines(fixedAsset);
    return fixedAsset;
  }
}
