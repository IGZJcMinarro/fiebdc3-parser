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
grammar MMeasurement;

import Lexer;
            
/*
REGISTRO TIPO MEDICIONES
~M | [ CODIGO_PADRE \ ] CODIGO_HIJO | { POSICION \ } | MEDICION_TOTAL | { TIPO \ COMENTARIO \ UNIDADES \ LONGITUD \ LATITUD \ ALTURA \ } | [ETIQUETA] |
ej:
~M | 502mar#        \   PP14052     | 1\1\1\1\2\     | 0              |                                                                  |
~M | 502mar#        \   PP07015     | 1\1\1\1\3\     | 3.57           |        \ 0 a 7      \ 3        \ 0.21     \         \        \   |
*/
mMeasurement: 'M' FIELDSEP ( mParentCode SUBFIELDSEP )+ mChildCode FIELDSEP ( mPosition SUBFIELDSEP )* FIELDSEP mTotalMeasurement FIELDSEP (mLine)* FIELDSEP (mLabel FIELDSEP)?;

mLine: mType? SUBFIELDSEP mComment? SUBFIELDSEP mUnits? SUBFIELDSEP mLongitude? SUBFIELDSEP mLatitude? SUBFIELDSEP mHeight? SUBFIELDSEP;

/*
CODIGO_PADRE: CODIGO del concepto padre o concepto descompuesto del presupuesto.
*/
mParentCode: TEXT;

/*
CODIGO_HIJO: CODIGO del concepto hijo o concepto de la línea de descomposición.
*/
mChildCode: TEXT;

/*
POSICION: Posición del CONCEPTO_HIJO en la descomposición del CONCEPTO_PADRE, 
este dato permite identificar la medición cuando la descomposición del concepto 
padre incluye varios conceptos hijo con el mismo CODIGO, la numeración de las 
posiciones comenzará con el 1.

El campo POSICION deberá especificarse siempre en intercambio de presupuestos 
cuando éste sea completo y estructurado, e indicará el camino completo de la 
medición descrita en la estructura del archivo. Por ejemplo 3 \ 5 \ 2, indicará 
la medición correspondiente al capítulo 3 del archivo; subcapítulo 5 del 
capítulo 3; y partida 2 del subcapítulo 5. En mediciones no estructuradas este 
campo es opcional.
*/
mPosition: TEXT;

/*
MEDICION_TOTAL: Debe coincidir con el rendimiento del registro tipo '~D' correspondiente.
Incorpora el sumatorio del producto de unidades, longitud, latitud y altura o el 
resultado de expresiones de cada línea, al leer este registro se recalculará este valor.
*/
mTotalMeasurement: TEXT;

/*
ETIQUETA: Es opcional, surge de la necesidad de transmitir un identificador de 
los conceptos (capítulos, subcapítulos o partidas). Este identificador lo 
imprimen, diversos programas, en los listados de mediciones o presupuesto de una
Obra (por ejemplo, ‘2.10’,  ‘A-27b’, ‘001001’,…); siendo único para cada concepto
(capítulo, subcapítulo o partida) y, en general, diferente de la codificación de
la base de datos empleada para confeccionar el presupuesto (El ‘CODIGO_HIJO’ 
muchas veces no aparece en los listados mencionados).
*/
mLabel: TEXT;

/*
TIPO: Indica el tipo de línea de medición de que se trate. Usualmente este 
subcampo estará vacío. Los tipos establecidos en esta VERSION son:
'1': Subtotal parcial: En esta línea aparecerá el subtotal de las líneas 
anteriores desde el último subtotal hasta la línea inmediatamente anterior a ésta.
'2': Subtotal acumulado: En esta línea aparecerá el subtotal de todas las líneas 
anteriores desde la primera hasta la línea inmediatamente anterior a ésta.
'3':        Expresión: Indicará que en el subcampo COMENTARIO aparecerá una 
expresión algebraica a evaluar. Se podrán utilizar los operadores 
'(', ')', '+', '-', '*', '/' y '^'; las variables 'a', 'b', 'c' y 'd' 
(que tendrán  por valor las cantidades introducidas en los subcampos UNIDADES, 
LONGITUD, LATITUD y ALTURA respectivamente); y la constante 'p' para el 
valor  Pi=3.1415926.  Esta expresión será válida hasta la siguiente línea de 
medición en la que se defina otra expresión. Solo se evalúa la expresión y no 
se multiplica por las unidades. Las expresiones fórmulas utilizan los criterios 
definidos en el anexo 2.
*/
mType: TEXT;

/*
COMENTARIO: Texto en la línea de medición. Podrá ser un comentario o una 
expresión algebraica.
*/
mComment: TEXT;

/*
UNIDADES, LONGITUD, LATITUD, ALTURA: Cuatro número reales con las mediciones. 
Si alguna magnitud no existe se dejará este campo vacío
*/
mUnits: TEXT;
mLongitude: TEXT;
mLatitude: TEXT;
mHeight: TEXT;
