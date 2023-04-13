{
    "type": "template",
    "comments": "This Gloop template demonstrates the use of a random step to randomly include a tag step, you can learn more about it [here](https://docs.torocloud.com/martini/latest/developing/gloop-template/step/tag/#random-inclusion).",
    "steps": [
        {
            "type": "containerTag",
            "children": [
                {
                    "type": "containerTag",
                    "tagName": "p",
                    "content": "Quote of the day:"
                },
                {
                    "type": "random",
                    "comments": "will randomly include one of the tags under this step",
                    "children": [
                        {
                            "type": "containerTag",
                            "tagName": "p",
                            "content": "It's not a bug, it's a \"feature\"."
                        },
                        {
                            "type": "containerTag",
                            "tagName": "p",
                            "content": "Think big then double it."
                        }
                    ]
                }
            ],
            "tagName": "div"
        }
    ]
}