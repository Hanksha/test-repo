{
    "type": "template",
    "comments": "Util Gloop template which includes the static resources such as `examples/web/static/css/examples-templates.css`. This Gloop template is invoked by other Gloop templates in this package in order to not manually repeat  the `head` tag.\n\nYou can learn more about static resources usage [here](https://docs.torocloud.com/martini/latest/developing/gloop-template/resource/)",
    "steps": [
        {
            "type": "containerTag",
            "children": [
                {
                    "type": "containerTag",
                    "comments": "include the `examples-template.css` file",
                    "tagName": "link",
                    "attributes": {
                        "href": "/examples/static/css/examples-template.css",
                        "rel": "stylesheet"
                    }
                }
            ],
            "tagName": "head"
        }
    ]
}