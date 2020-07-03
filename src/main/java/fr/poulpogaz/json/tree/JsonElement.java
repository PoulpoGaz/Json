package fr.poulpogaz.json.tree;

public interface JsonElement {

    boolean isObject();

    boolean isArray();

    boolean isValue();
}