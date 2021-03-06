/*
 * FIEBDC 3 parser  
 * Copyright (C) 2014 DiSiD Technologies
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/copyleft/gpl.html>.
 */

package com.disid.fiebdc3;

import java.io.IOException;
import java.io.Reader;

/**
 * API of the service to parse Fiebdc 3 files.
 * <p>
 * Usage: <code><pre>
 * File testFile = new File("test.bc3");
 * Reader testReader = new FileReader(testFile);
 * Fiebdc3Service service = new AntlrFiebdc3Service();
 * Database database = service.parse(testReader);
 * </pre></code>
 * </p>
 * 
 * @author DiSiD Team
 */
public interface Fiebdc3Service {
    
    /**
     * Parses the given file in Fiebdc 3 format.
     * @param fiebdcData the data to parse
     * @return a model of the parsed data
     * @throws IOException if there is an IO error reading the data
     */
    Database parse(Reader fiebdcData) throws IOException;

}
