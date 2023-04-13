{
    "type": "template",
    "comments": "This Gloop template demonstrates how to mark a tag step with the `$gloopTemplateView` label so it can be injected when invoked.\n\nYou can learn more about it [here](https://docs.torocloud.com/martini/latest/developing/gloop-template/step/invoke-template/#injecting-additional-tags-to-an-invoked-template).\n\nTo try it out, open the `examples/code/template/invokeTemplate/Main.gtpl` file and invoke it.",
    "input": [
        {
            "name": "firstName",
            "allowNull": false,
            "defaultValue": "John"
        },
        {
            "name": "lastName",
            "allowNull": false,
            "defaultValue": "Doe"
        },
        {
            "name": "age",
            "type": "integer",
            "allowNull": false,
            "defaultValue": 18
        }
    ],
    "steps": [
        {
            "type": "containerTag",
            "children": [
                {
                    "type": "containerTag",
                    "tagName": "p",
                    "content": "First Name:"
                },
                {
                    "type": "containerTag",
                    "tagName": "p",
                    "lines": [
                        {
                            "from": [
                                "firstName"
                            ],
                            "to": [
                                "$tagContent"
                            ]
                        }
                    ]
                },
                {
                    "type": "containerTag",
                    "tagName": "p",
                    "content": "Last Name:"
                },
                {
                    "type": "containerTag",
                    "tagName": "p",
                    "lines": [
                        {
                            "from": [
                                "lastName"
                            ],
                            "to": [
                                "$tagContent"
                            ]
                        }
                    ]
                },
                {
                    "type": "containerTag",
                    "tagName": "p",
                    "content": "Age:"
                },
                {
                    "type": "containerTag",
                    "tagName": "p",
                    "lines": [
                        {
                            "from": [
                                "age"
                            ],
                            "to": [
                                "$tagContent"
                            ]
                        }
                    ]
                },
                {
                    "type": "containerTag",
                    "label": "$gloopTemplateView",
                    "comments": "The $gloopTemplateView label marks the tag which can be injected by other invoking Gloop templates. \n\nWhen this template is invoked using an invoke Gloop template step, the children of that step will be added here. \n\nYou can learn more about it [here](https://docs.torocloud.com/martini/latest/developing/gloop-template/step/invoke-template/#injecting-additional-tags-to-an-invoked-template).",
                    "tagName": "div"
                }
            ],
            "tagName": "div",
            "attributes": {
                "class": "person"
            }
        }
    ]
}