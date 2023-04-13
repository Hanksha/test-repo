{
    "type": "template",
    "comments": "This Gloop template demonstrates how to map properties to a tag attribute and tag content, you can learn more about that [here](https://docs.torocloud.com/martini/latest/developing/gloop-template/step/tag/#dynamically-setting-the-value-of-a-tags-content).",
    "input": [
        {
            "name": "userType",
            "allowNull": false,
            "defaultValue": "admin",
            "choices": [
                "admin",
                "user"
            ]
        },
        {
            "name": "username",
            "allowNull": false,
            "defaultValue": "Togo"
        }
    ],
    "steps": [
        {
            "type": "containerTag",
            "comments": "set the style for the admin and user classes",
            "tagName": "style",
            "content": ".admin {\n    color: red;\n}\n.user {\n    color: blue;\n}"
        },
        {
            "type": "containerTag",
            "children": [
                {
                    "type": "containerTag",
                    "comments": "map the userType property to the class attribute and the username to the tag content",
                    "tagName": "p",
                    "attributes": {
                        "class": ""
                    },
                    "lines": [
                        {
                            "from": [
                                "userType"
                            ],
                            "to": [
                                "class"
                            ]
                        },
                        {
                            "from": [
                                "username"
                            ],
                            "to": [
                                "$tagContent"
                            ]
                        }
                    ]
                }
            ],
            "tagName": "div",
            "content": "Username:"
        }
    ]
}