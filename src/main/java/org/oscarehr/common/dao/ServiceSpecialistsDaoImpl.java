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
import org.oscarehr.common.model.ProfessionalSpecialist;
import org.oscarehr.common.model.ServiceSpecialists;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceSpecialistsDaoImpl extends AbstractDaoImpl<ServiceSpecialists> implements ServiceSpecialistsDao {

    public ServiceSpecialistsDaoImpl() {
        super(ServiceSpecialists.class);    
    }
    
    @Override
    public List<ServiceSpecialists> findByServiceId(int serviceId) {
        Query q = entityManager.createQuery("select x from ServiceSpecialists x where x.id.serviceId = ?");
        q.setParameter(0, serviceId);
        
        @SuppressWarnings("unchecked")
        List<ServiceSpecialists> results = q.getResultList();
        
        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findSpecialists(Integer servId) {
        String sql = "FROM ServiceSpecialists ser, " + ProfessionalSpecialist.class.getSimpleName() + " pro " +
                "WHERE pro.id = ser.id.specId and ser.id.serviceId = :serviceId " +
                "ORDER BY pro.lastName";
        Query query = entityManager.createQuery(sql);
        query.setParameter("serviceId", servId);
        return query.getResultList();
    }
}
