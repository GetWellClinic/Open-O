/**
 * Copyright (c) 2024. Magenta Health. All Rights Reserved.
 * Copyright (c) 2001-2002. Department of Family Medicine, McMaster University. All Rights Reserved.
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
 * This software was written for the
 * Department of Family Medicine
 * McMaster University
 * Hamilton
 * Ontario, Canada
 *
 * Modifications made by Magenta Health in 2024.
 */
package org.oscarehr.common.dao;

import java.util.List;
import javax.persistence.Query;
import org.oscarehr.common.model.ReportByExamplesFavorite;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class ReportByExamplesFavoriteDaoImpl extends AbstractDaoImpl<ReportByExamplesFavorite> implements ReportByExamplesFavoriteDao {

    public ReportByExamplesFavoriteDaoImpl() {
        super(ReportByExamplesFavorite.class);
    }

    @Override
    public List<ReportByExamplesFavorite> findByQuery(String query) {
        Query q = createQuery("ex", "ex.query LIKE :query");
        q.setParameter("query", query);
        return q.getResultList();
    }

    @Override
    public List<ReportByExamplesFavorite> findByEverything(String providerNo, String favoriteName, String queryString) {
        Query query = createQuery("ex", "ex.providerNo = :providerNo AND ex.name LIKE :name OR ex.query LIKE :query");
        query.setParameter("providerNo", providerNo);
        query.setParameter("name", favoriteName);
        query.setParameter("query", queryString);
        return query.getResultList();
    }

    @Override
    public List<ReportByExamplesFavorite> findByProvider(String providerNo) {
        Query query = createQuery("ex", "ex.providerNo = :providerNo");
        query.setParameter("providerNo", providerNo);
        return query.getResultList();
    }
}
