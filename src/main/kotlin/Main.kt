package org.example

import org.gnome.adw.ApplicationWindow
import org.gnome.adw.HeaderBar
import org.gnome.adw.ToolbarView
import org.gnome.gtk.Application
import org.gnome.gtk.Button


fun activate(app: Application) {
    val window = ApplicationWindow(app).apply {
        title = "GTK Kotlin App"
        setDefaultSize(600, 400)
    }
    val ui = {
        var n = 1

        val btn = Button.withLabel("Click me!").apply {
            onClicked {
                label = "Clicked ${n}!"
                n += 1
            }
        }
        val header = HeaderBar()

        ToolbarView().apply {
            addTopBar(header)
            content = btn
        }
    }
    window.content = ui()
    window.present()
}


fun main(args: Array<String>) {
    val app = Application("my.example.HelloApp")
    app.onActivate { activate(app) }
    app.run(args)
}