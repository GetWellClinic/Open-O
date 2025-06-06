/**
 * Copyright (c) 2024. Magenta Health. All Rights Reserved.
 *
 * Copyright (c) 2005-2012. Centre for Research on Inner City Health, St. Michael's Hospital, Toronto. All Rights Reserved.
 * This software is published under the GPL GNU General Public License.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 * This software was written for
 * Centre for Research on Inner City Health, St. Michael's Hospital,
 * Toronto, Ontario, Canada
 *
 * Modifications made by Magenta Health in 2024.
 */

package org.oscarehr.common.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.oscarehr.common.model.Allergy;

public interface AllergyDao extends AbstractDao<Allergy> {

	public List<Allergy> findAllergies(Integer demographic_no);

	public List<Allergy> findActiveAllergies(Integer demographic_no);

	public List<Allergy> findActiveAllergiesOrderByDescription(Integer demographic_no);

	public List<Allergy> findByDemographicIdUpdatedAfterDate(Integer demographicId, Date updatedAfterThisDate);

	public List<Integer> findDemographicIdsUpdatedAfterDate(Date updatedAfterThisDate);

	public List<Allergy> findByUpdateDate(Date updatedAfterThisDateInclusive, int itemsToReturn);

	public List<Allergy> findByProviderDemographicLastUpdateDate(String providerNo, Integer demographicId,
			Date updatedAfterThisDateExclusive, int itemsToReturn);

	public List<Allergy> findAllCustomAllergiesWithNullNonDrugFlag(int start, int limit);
}
