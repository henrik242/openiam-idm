/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.synch.file;

import org.mule.api.transformer.DataType;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

/**
 * Tranformer used for CSV files during the synchronization process.
 * @author suneet
 *
 */
public class CsvFileTransformer extends AbstractTransformer  {

	/* (non-Javadoc)
	 * @see org.mule.transformer.AbstractTransformer#doTransform(java.lang.Object, java.lang.String)
	 */
	@Override
	protected Object doTransform(Object src, String encoding) 	throws TransformerException {
		System.out.println("do transform called.");
		return null;
	}

	

}
