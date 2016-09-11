package br.edu.ifpb.sicAgro.beans.dashboard;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

import br.edu.ifpb.sicAgro.model.Produto;
import br.edu.ifpb.sicAgro.services.ItemCargaService;

@Named
@ViewScoped
public class TimeLineBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemCargaService itemCargaService;
	
	private TimelineModel model;  
	   
    private boolean selectable = true;  
    private boolean zoomable = true;  
    private boolean moveable = true;  
    private boolean stackEvents = true;  
    private String eventStyle = "box";  
    private boolean axisOnTop;  
    private boolean showCurrentTime = true;  
    private boolean showNavigation = false;  
   
    @PostConstruct 
    protected void initialize() {  
        model = new TimelineModel();  
        
        this.generateTimeLine();
   
/*        Calendar cal = Calendar.getInstance();      
        cal.set(2014, Calendar.JUNE, 12, 0, 0, 0);  
        model.add(new TimelineEvent("PrimeUI 1.1", cal.getTime()));  */
           
    }  
    
    private void generateTimeLine(){
		List<Object[]> listProdutos = itemCargaService.getProdutosAndDates();
		Calendar calendar = Calendar.getInstance();

		if (listProdutos != null) {
			for (Object[] objects : listProdutos) {
				Produto produto = (Produto) objects[0];
				Date date = (Date) objects[1];
				calendar.setTime(date);
				model.add(new TimelineEvent(produto.getName(), calendar.getTime()));    
			}
		}
    }
   
    public void onSelect(TimelineSelectEvent e) {  
        TimelineEvent timelineEvent = e.getTimelineEvent();  
   
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected event:", timelineEvent.getData().toString());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
   
    public TimelineModel getModel() {  
        return model;  
    }  
   
    public boolean isSelectable() {  
        return selectable;  
    }  
   
    public void setSelectable(boolean selectable) {  
        this.selectable = selectable;  
    }  
   
    public boolean isZoomable() {  
        return zoomable;  
    }  
   
    public void setZoomable(boolean zoomable) {  
        this.zoomable = zoomable;  
    }  
   
    public boolean isMoveable() {  
        return moveable;  
    }  
   
    public void setMoveable(boolean moveable) {  
        this.moveable = moveable;  
    }  
   
    public boolean isStackEvents() {  
        return stackEvents;  
    }  
   
    public void setStackEvents(boolean stackEvents) {  
        this.stackEvents = stackEvents;  
    }  
   
    public String getEventStyle() {  
        return eventStyle;  
    }  
   
    public void setEventStyle(String eventStyle) {  
        this.eventStyle = eventStyle;  
    }  
   
    public boolean isAxisOnTop() {  
        return axisOnTop;  
    }  
   
    public void setAxisOnTop(boolean axisOnTop) {  
        this.axisOnTop = axisOnTop;  
    }  
   
    public boolean isShowCurrentTime() {  
        return showCurrentTime;  
    }  
   
    public void setShowCurrentTime(boolean showCurrentTime) {  
        this.showCurrentTime = showCurrentTime;  
    }  
   
    public boolean isShowNavigation() {  
        return showNavigation;  
    }  
   
    public void setShowNavigation(boolean showNavigation) {  
        this.showNavigation = showNavigation;  
    }
}
