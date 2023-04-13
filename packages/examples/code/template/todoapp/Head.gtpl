{
    "type": "template",
    "comments": "This Gloop template is used by all the Gloop template of the todo app to add the `head` tag and include the css file and font.\n\nYou can learn more about static resources usage [here](https://docs.torocloud.com/martini/latest/developing/gloop-template/resource/)",
    "steps": [
        {
            "type": "containerTag",
            "label": "$gloopTemplateView",
            "comments": "mark this tag with the `$gloopTemplateView` label so it can be injected by other Gloop templates",
            "children": [
                {
                    "type": "containerTag",
                    "tagName": "meta",
                    "attributes": {
                        "charset": "utf-8"
                    }
                },
                {
                    "type": "containerTag",
                    "comments": "include the css file at examples/web/static/css/todoapp.css",
                    "tagName": "link",
                    "attributes": {
                        "rel": "stylesheet",
                        "type": "text/css",
                        "href": "/examples/static/css/todoapp.css"
                    }
                },
                {
                    "type": "containerTag",
                    "tagName": "link",
                    "attributes": {
                        "rel": "stylesheet",
                        "href": "https://fonts.googleapis.com/css?family=Roboto:400,500,700"
                    }
                }
            ],
            "tagName": "head"
        }
    ]
}