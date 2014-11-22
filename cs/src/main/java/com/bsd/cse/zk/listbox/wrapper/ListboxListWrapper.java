/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsd.cse.zk.listbox.wrapper;

import com.bsd.cse.zk.grid.wrapper.*;
import com.bsd.cse.common.ConvertFormatUtil;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.naming.directory.SearchControls;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.FieldComparator;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Paging;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.event.PagingEvent;

/**
 *
 * @author thanasith
 */
public class ListboxListWrapper<E> extends ListModelList implements Serializable {
    private static Log LOG = LogFactory.getLog(ListboxListWrapper.class);
    private static final long serialVersionUID = 3859459937623249455L;

    
    
    protected Listbox _listbox;
    protected Paging paging;
    private HibernateSearchObject<E> hibernateSearchObject;
    private List<E> _list;
    private Boolean supportPaging = true;    
    
    public ListboxListWrapper(Listbox _listbox,Paging _paging,List _initailList,HibernateSearchObject _searchObj)
    {
        super(_initailList);
        this._list = _initailList;
        this._listbox = _listbox;
        this.hibernateSearchObject = _searchObj;
        this.paging = _paging;           
        this.paging.setTotalSize(_initailList.size());        
        setListeners();
    }
    
    public ListboxListWrapper(Listbox _listbox,Paging _paging,GridSearchResult _searchResult,HibernateSearchObject _searchObj)
    {
        super(_searchResult.getList());
        this._list = _searchResult.getList();
        this._listbox = _listbox;
        this.hibernateSearchObject = _searchObj;
        this.paging = _paging; 
        this.paging.setTotalSize(_searchResult.getCount());
        setListeners();
    }
    
    private void setListeners()
    {
        Iterator pagingItr = paging.getListenerIterator("onPaging");
        while(pagingItr.hasNext())
        {
            paging.removeEventListener("onPaging", (EventListener)pagingItr.next());
        }
        
        paging.addEventListener("onPaging", new OnPagingEventListener());

        Listhead columns = _listbox.getListhead();

        List list = columns.getChildren();

        for(Object object : list)
        {
            if(object instanceof Listheader)
            {
                Listheader lheader = (Listheader)object;
                if(lheader.getSortAscending() != null || lheader.getSortDescending() != null)
                {
                    Iterator lheaderItr = lheader.getListenerIterator("onSort");
                    while(lheaderItr.hasNext())
                    {
                        lheader.removeEventListener("onSort", (EventListener)lheaderItr.next());
                    }
                    
                    lheader.addEventListener("onSort",new OnSortEventListener());
                }
            }
        }
        
        
    }
    
    private class OnPagingEventListener implements EventListener {

        @Override
        public void onEvent(Event event) throws Exception {
            PagingEvent p = (PagingEvent) event;
            
            Integer start  = p.getActivePage()*paging.getPageSize();
            if(LOG.isDebugEnabled())
            {
                LOG.debug("-->id : "+p.getTarget().getId());
                LOG.debug("--> : "+start+"/"+paging.getPageSize());
            }
            refreshModel(start,paging.getPageSize());
            
        }
    }
    
    private class OnSortEventListener implements EventListener {


        @Override
        public void onEvent(Event event) throws Exception {
            final Listheader column = (Listheader)event.getTarget();
            final String sortDirection = column.getSortDirection();
            
            if("ascending".equals(sortDirection))
            {
                final Comparator cmpr = column.getSortDescending();
                if(cmpr instanceof FieldComparator )                        
                {
                    String orderBy = ((FieldComparator)cmpr).getOrderBy();
                    orderBy = orderBy.replace("ASC","").trim();                    
                    hibernateSearchObject.clearSort();
                    hibernateSearchObject.addSort(orderBy, true);
                }
            }
            else
            {
                final Comparator cmpr = column.getSortAscending();
                if(cmpr instanceof FieldComparator )                        
                {
                    String orderBy = ((FieldComparator)cmpr).getOrderBy();
                    orderBy = orderBy.replace("DESC","").trim();                    
                    hibernateSearchObject.clearSort();
                    hibernateSearchObject.addSort(orderBy, false);
                }
            }                        
            
            if(supportPaging)
            {                
                refreshModel(0, paging.getPageSize());                
                paging.setActivePage(0);                
            }
            
            
        }
    }
    
    private void refreshModel(Integer start,Integer pageSize) throws Exception
    {
        _list.clear();
        GridSearchResult<E> searchResult = hibernateSearchObject.getSearchResult(start, pageSize);
        _list.addAll(searchResult.getList());
        _listbox.setModel(new SimpleListModel(_list)
        {
            public void sort(Comparator cmpr, boolean ascending)
            {
                
            }
        });        
        paging.setTotalSize(searchResult.getCount());        
        
    }
    
   
    
}
