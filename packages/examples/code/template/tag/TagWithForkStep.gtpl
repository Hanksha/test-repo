{
    "type": "template",
    "comments": "This Gloop template demonstrates the use of a fork step to conditionally include a tag step, you can learn more about it [here](https://docs.torocloud.com/martini/latest/developing/gloop-template/step/tag/#conditional-inclusion).",
    "input": [
        {
            "name": "language",
            "allowNull": false,
            "defaultValue": "en",
            "choices": [
                "en",
                "fr"
            ]
        }
    ],
    "steps": [
        {
            "type": "containerTag",
            "children": [
                {
                    "type": "fork",
                    "comments": "fork on the language property",
                    "expression": "language",
                    "children": [
                        {
                            "type": "containerTag",
                            "label": "en",
                            "comments": "will be used if the value of language is 'en'",
                            "tagName": "h1",
                            "content": "Hello World!"
                        },
                        {
                            "type": "containerTag",
                            "label": "fr",
                            "comments": "will be user if the value of language is 'fr'",
                            "tagName": "h1",
                            "content": "Bonjour le Monde !"
                        }
                    ]
                }
            ],
            "tagName": "div"
        }
    ]
}