package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

    @Autowired
    private WidgetRepository widgetRepository;

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/api/widget")
    public List<Widget> findAllWidgets(){
        return (List<Widget>) widgetRepository.findAll();
    }

    @GetMapping("/api/widget/{widgetId}")
    public Optional<Widget> findWidgetByWidgetId(@PathVariable("widgetId") int id){
        return widgetRepository.findById(id);
    }

    @GetMapping("/api/topic/{topicId}/widget")
    public List<Widget> findAllWidgetForTopic(@PathVariable("topicId") int topicId){
        Optional<Topic> data = topicRepository.findById(topicId);
        if(data.isPresent()){
            return widgetRepository.findAllWidgetsForTopic(data.get());
        }
        return null;
    }

    @PostMapping("/api/topic/{topicId}/savewidget")
    public void saveWidgets(@PathVariable("topicId") int topicId, @RequestBody List<Widget> widgets){
        Optional<Topic> data = topicRepository.findById(topicId);

        if(data.isPresent()){
            Topic topic = data.get();
            topic.getWidgets().forEach((w) -> widgetRepository.delete(w));

            int i=0;
            for(Widget widget: widgets){
                widget.setId(null);
                widget.setTopic(topic);
                widget.setOrder(i++);
                widgetRepository.save(widget);
            }
        }
    }


    @PostMapping("/api/topic/{topicId}/widget/save")
    public Widget createWidget(@PathVariable("topicId") int topicId, @RequestBody Widget widget){
        Optional<Topic> data = topicRepository.findById(topicId);

        if(data.isPresent()){
            //widget.setTopic(data.get());
            return widgetRepository.save(widget);
        }

        return null;
    }

    @PutMapping("/api/widget/{widgetId}")
    public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget newWidget){
        Optional<Widget> data = widgetRepository.findById(widgetId);

        if(data.isPresent()){
            Widget widget = data.get();
            if(newWidget.getName()!=null){
                widget.setName(newWidget.getName());
            }
            if(newWidget.getOrder()!=null){
                widget.setOrder(newWidget.getOrder());
            }
            if(newWidget.getText()!=null){
                widget.setText(newWidget.getText());
            }
            if(newWidget.getClassName()!=null){
                widget.setClassName(newWidget.getClassName());
            }
            if(newWidget.getStyle()!=null){
                widget.setStyle(newWidget.getStyle());
            }
            if(newWidget.getWidth()!=null){
                widget.setWidth(newWidget.getWidth());
            }
            if(newWidget.getHeight()!=null){
                widget.setHeight(newWidget.getHeight());
            }
            if(newWidget.getWidgetType()!=null){
                widget.setWidgetType(newWidget.getWidgetType());
            }
            if(newWidget.getSize()!=null){
                widget.setSize(newWidget.getSize());
            }
            if(newWidget.getHref()!=null){
                widget.setHref(newWidget.getHref());
            }
            if(newWidget.getSrc()!=null){
                widget.setSrc(newWidget.getSrc());
            }
            if(newWidget.getListItems()!=null){
                widget.setListItems(newWidget.getListItems());
            }
           /* if(newWidget.getListType()!=null){
                widget.setListType(newWidget.getListType());
            }*/
           return widgetRepository.save(widget);
        }
        return null;
    }

    @DeleteMapping("/api/widget/{widgetId}")
    public void deleteWidget(@PathVariable("widgetId") int widgetId){
        widgetRepository.deleteById(widgetId);
    }



}
