{
    "type": "template",
    "comments": "This Gloop template demonstrates the use of a while step to repetitively include a tag step, you can learn more about it [here](https://docs.torocloud.com/martini/latest/developing/gloop-template/step/tag/#repetitive-inclusion).",
    "input": [
        {
            "name": "count",
            "type": "integer",
            "allowNull": false,
            "defaultValue": 4
        }
    ],
    "steps": [
        {
            "type": "containerTag",
            "children": [
                {
                    "type": "while",
                    "comments": "the while step will include the tag until the condition is false",
                    "expression": "$gloopIndex < count",
                    "evaluate": true,
                    "children": [
                        {
                            "type": "containerTag",
                            "comments": "the `li` tag will be included up to `count` times",
                            "tagName": "li",
                            "lines": [
                                {
                                    "from": [
                                        "$gloopCount"
                                    ],
                                    "to": [
                                        "$tagContent"
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ],
            "tagName": "ul"
        }
    ]
}