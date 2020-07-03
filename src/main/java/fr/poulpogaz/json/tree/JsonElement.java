package fr.poulpogaz.json.tree;

/**
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public interface JsonElement {

    boolean isObject();

    boolean isArray();

    boolean isValue();
}