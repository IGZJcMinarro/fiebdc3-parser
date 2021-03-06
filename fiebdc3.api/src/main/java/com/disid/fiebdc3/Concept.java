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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Concept of a Fiebdc 3 database.
 * 
 * @author DiSiD Team
 */
public class Concept {

    private String code;

    private String measureUnit;

    private String summary;

    private List<Float> prices = new ArrayList<Float>();

    private List<Date> lastUpdates = new ArrayList<Date>();

    private String type;

    private String description;

    /**
     * Creates a concept with the given code.
     * 
     * @param code
     *            which uniquely identifies a concept
     */
    Concept(String code) {
        this.code = code;
    }

    /**
     * Spec definition:<br/>
     * <i>CODIGO: CODIGO del concepto descrito. Un concepto puede tener varios
     * CODIGOs que actuarán como sinónimos, este mecanismo permite integrar
     * distintos sistemas de clasificación. Puede tener un máximo de 20
     * caracteres. </i>
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Spec definition:<br/>
     * <i> UNIDAD: Unidad de medida. Existe una relación de unidades de medida
     * recomendadas, elaborada por la Asociación de Redactores de Bases de Datos
     * de CONSTRUCCION. Véase el Anexo 7 sobre Unidades de Medida.</i>
     */
    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    /**
     * Spec definition:<br/>
     * <i>RESUMEN: Resumen del texto descriptivo. Cada soporte indicará el
     * número de caracteres que admite en su campo resumen. Se recomienda un
     * máximo de 64 caracteres.</i>
     */
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Spec definition:<br/>
     * <i>PRECIO: Precio del concepto. Un concepto puede tener varios precios
     * alternativos que representen distintas épocas, ámbitos geográficos, etc.,
     * definidos biunívocamente respecto al campo [CABECERA \
     * {ROTULO_IDENTIFICACION\} del registro ~V. Cuando haya más de un precio se
     * asignarán secuencialmente a cada ROTULO definido; si hay más ROTULOS que
     * precios, se asignará a aquellos el último precio definido. En el caso que
     * el concepto posea descomposición, este precio será el resultado de dicha
     * descomposición y se proporcionará, de forma obligatoria, para permitir su
     * comprobación. En caso de discrepancia, tendrá preponderancia el resultado
     * obtenido por la descomposición, tal como se indica en el registro Tipo
     * Descomposición, ~D, y complementariamente se podría informar al usuario
     * de dicha situación. Esto se aplica también a los conceptos tipo capítulo
     * y concepto raíz de una Obra o Presupuesto. Como excepción a esta regla
     * está el intercambio de mediciones no estructuradas (véase la descripción
     * del registro Tipo Mediciones, ~M).</i>
     */
    public List<Float> getPrices() {
        return prices;
    }

    public void addPrice(Float price) {
        this.prices.add(price);
    }

    /**
     * Return the main (first) price of the concept.
     * 
     * @return the main (first) price of the concept
     */
    public Float getMainPrice() {
        return prices.get(0);
    }

    /**
     * Spec definition:<br/>
     * <i>FECHA: Fecha de la última actualización del precio. Cuando haya más de
     * una fecha se asignarán secuencialmente a cada precio definido, si hay más
     * precios que fechas, los precios sin su correspondiente fecha tomarán la
     * última fecha definida.</i>
     */
    public List<Date> getLastUpdates() {
        return lastUpdates;
    }

    public void addLastUpdate(Date lastUpdate) {
        this.lastUpdates.add(lastUpdate);
    }

    /**
     * Return the main (first) lastUpdate of the concept.
     * 
     * @return the main (first) lastUpdate of the concept
     */
    public Date getMainLastUpdate() {
        return lastUpdates.get(0);
    }

    /**
     * Spec definition:<br/>
     * <i>TIPO: Tipo de concepto, Inicialmente se reservan los siguientes tipos:
     * 0 (Sin clasificar) 1 (Mano de obra), 2 (Maquinaria y medios aux.), 3
     * (Materiales). También se permite (y aconseja) utilizar la clasificación
     * indicada por el BOE y la CNC en índices y fórmulas polinómicas de
     * revisión de precios así como los aconsejados por la Asociación de
     * Redactores de Bases de Datos de la Construcción. En el Anexo 4 aparecen
     * los tipos actualmente vigentes.</i>
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Spec definition:<br/>
     * <i>TEXTO_DESCRIPTIVO: Texto descriptivo del concepto sin limitación de
     * tamaño. El texto podrá contener caracteres fin de línea (ASCII-13 +
     * ASCII-10) que se mantendrán al reformatearlo.</i>
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Concept {" + "Code: " + code + ", Summary: " + summary
                + ", Type: " + type + ", Measure unit: " + measureUnit
                + ", Prices: " + prices + ", Last updates: " + lastUpdates
                + ", Description: " + description + "}";
    }
}
