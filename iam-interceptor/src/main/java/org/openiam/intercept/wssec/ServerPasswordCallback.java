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
package org.openiam.intercept.wssec;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;


/**
 * @author suneet
 *
 */
public class ServerPasswordCallback implements CallbackHandler  {

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

		System.out.println("ServerPasswordCallback called...1");
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

        System.out.println("Identitier=" + pc.getIdentifier());
        System.out.println("Identitier=" + pc.getCustomToken());
        
        if (pc.getIdentifier().equals("joe")) {
            // set the password on the callback. This will be compared to the
            // password which was sent from the client.
            pc.setPassword("password");
        }
    }

	
}
