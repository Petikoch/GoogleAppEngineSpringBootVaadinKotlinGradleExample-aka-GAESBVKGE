package ch.petikoch.examples.gaesbvkge.ui.vaadin

import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route
class MainView : VerticalLayout() {
    init {
        add(Text("Hello World!"))
        add(Button("Click me") { e -> Notification.show("Hello Google AppEngine Spring Boot Vaadin Kotlin User!") })
    }
}