/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsd.cse.zk.grid.wrapper;

import com.bsd.cse.common.ConvertFormatUtil;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import java.io.Serializable;
import java.util.Comparator;
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
import org.zkoss.zul.Paging;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.event.PagingEvent;

/**
 *
 * @author thanasith
 */
public class GridListWrapper<E> extends ListModelList implements Serializable {
    private static Log LOG = LogFactory.getLog(GridListWrapper.class);

    
    
    protected Grid grid;
    protected Paging paging;
    private HibernateSearchObject<E> hibernateSearchObject;
    private List<E> list;
    private Boolean supportPaging = true;
    private Label totalRecordsLbl;
    
    public GridListWrapper(Grid _grid,Paging _paging,List _initailList,HibernateSearchObject _searchObj,Label totalRecordsLbl)
    {
        super(_initailList);
        this.list = _initailList;
        this.grid = _grid;
        this.hibernateSearchObject = _searchObj;
        this.paging = _paging;           
        this.paging.setTotalSize(_initailList.size());
        this.totalRecordsLbl = totalRecordsLbl;
        this.totalRecordsLbl.setValue(String.valueOf(this.paging.getTotalSize()));
        setListeners();
    }
    
    public GridListWrapper(Grid _grid,Paging _paging,GridSearchResult _searchResult,HibernateSearchObject _searchObj,Label totalRecordsLbl)
    {
        super(_searchResult.getList());
        this.list = _searchResult.getList();
        this.grid = _grid;
        this.hibernateSearchObject = _searchObj;
        this.paging = _paging; 
        this.paging.setTotalSize(_searchResult.getCount());
        this.totalRecordsLbl = totalRecordsLbl;
        this.totalRecordsLbl.setValue(String.valueOf(this.paging.getTotalSize()));
        setListeners();
    }

    public GridListWrapper(Grid _grid,Paging _paging,GridSearchResult _searchResult,HibernateSearchObject _searchObj)
    {
        super(_searchResult.getList());
        this.list = _searchResult.getList();
        this.grid = _grid;
        this.hibernateSearchObject = _searchObj;
        this.paging = _paging;
        this.paging.setTotalSize(_searchResult.getCount());
        setListeners();
    }

    public GridListWrapper(Grid _grid,Paging _paging,ListModelList list,HibernateSearchObject _searchObj)
    {
        super(list);
        this.list = list;
        this.grid = _grid;
        this.hibernateSearchObject = _searchObj;
        this.paging = _paging;
        this.paging.setTotalSize(list.getSize());
        setListeners();
    }
    
    private void setListeners()
    {
        paging.addEventListener("onPaging", new OnPagingEventListener());
        
        Columns columns = grid.getColumns();
        List list = columns.getChildren();
        
        for(Object object : list)
        {
            if(object instanceof Column)
            {
                Column lheader = (Column)object;
                if(lheader.getSortAscending() != null || lheader.getSortDescending() != null)
                {
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
                LOG.debug("--> : "+start+"/"+paging.getPageSize());
            }
            refreshModel(start,paging.getPageSize());
            
        }
    }
    
    private class OnSortEventListener implements EventListener {


        @Override
        public void onEvent(Event event) throws Exception {
            final Column column = (Column)event.getTarget();
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
        list.clear();
        GridSearchResult<E> searchResult = hibernateSearchObject.getSearchResult(start, pageSize);
        list.addAll(searchResult.getList());          
        grid.setModel(new SimpleListModel(list)
        {
            public void sort(Comparator cmpr, boolean ascending)
            {
                
            }
        });        
        paging.setTotalSize(searchResult.getCount());
        this.totalRecordsLbl.setValue(ConvertFormatUtil.convertFormat(this.paging.getTotalSize()));
        
    }
    
   
    
}
