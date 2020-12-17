package it.michdev.restwebservice.utils.adapter;

import java.util.ArrayList;

/**
 * L'interfaccia <b>IDataAdapter</b> definisce un <code>adapter</code> per
 * converite dati grezzi in oggetti di tipo <code>T</code> specificato come
 * parametro, per migliorare la gestione e la loro manipolazione.
 * 
 * @version 0.8.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.utils.adapter.LiveDataAdapter
 * @see it.michdev.restwebservice.utils.adapter.HistoricalDataAdapter
 */
public interface IDataAdapter<T> {

    /**
     * Crea un <code>ArrayList</code> di oggetti di tipo specificato come parametro
     * <code>T</code>, ottenuti dopo averl creati con i dati grezzi.
     * 
     * @return <code>ArrayList <code>T</code>
     */
    public ArrayList<T> createList();
}
