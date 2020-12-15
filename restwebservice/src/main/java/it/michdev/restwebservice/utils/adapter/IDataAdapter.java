package it.michdev.restwebservice.utils.adapter;

import java.util.ArrayList;

import it.michdev.restwebservice.model.CurrencyPair;

public interface IDataAdapter<T extends CurrencyPair>{

   public abstract ArrayList<T> createList();
}
