{
    "comment": "# [Time-Based Transition Delays](https://docs.torocloud.com/martini/latest/developing/services/flux)\n\nIn this [Flux service](https://docs.torocloud.com/martini/latest/developing/services/flux/), we're going to show how you can use a [transition's](https://docs.torocloud.com/martini/latest/developing/services/flux/anatomy/#transitions) `Wait` property to delay a transition to the next state.\n\n## Invoking the Service\n\n[Run this service via the Flux editor](https://docs.torocloud.com/martini/latest/developing/services/flux/running/) as you normally would. No [inputs](https://docs.torocloud.com/martini/latest/developing/services/overview/input-output/) are required. However, you must ensure the Start State is set to `Set_End_Date`.\n\n## Expected Output\n\n`Set_End_Date` will log a message like below to the console:\n\n```\nINFO  [Martini] Current date is Mon Apr 15 09:35:02 PHT 2019. Setting end date to 10 seconds from now. The state will transition into waiting state until it is 10 seconds after the time this service was executed.\n```\n\nBefore moving to `Print_End_Date`, the [transition](https://docs.torocloud.com/martini/latest/developing/services/flux/anatomy/#transitions) will make the Flux wait for 10 seconds. After 10 seconds, a log message will appear in the console:\n\n```\nINFO  [Martini] After 10 seconds, it finally transitions to Print End Date. End date was Mon Apr 15 09:45:03 PHT 2019.\n```\n\nNote that due to other running processes, the end date might be a couple of nanoseconds \n",
    "input": [
        {
            "name": "$trackerId",
            "comments": "Used for tracking the flux service"
        }
    ],
    "states": [
        {
            "name": "Set_End_Date",
            "displayName": "Set End Date",
            "x": 120,
            "y": 40,
            "comment": "This state uses a set step expression to set the endDate to 10 seconds from the time it is called.",
            "action": {
                "type": "invokeCode",
                "className": "io.toro.martini.LoggerMethods",
                "methodName": "info",
                "parameters": [
                    "String"
                ],
                "inputs": [
                    {
                        "type": "set",
                        "expression": "\"Current date is ${new Date()}. Setting end date to 10 seconds from now. The state will transition into waiting state until it is 10 seconds after the time this service was executed.\"",
                        "evaluate": true,
                        "to": [
                            "message"
                        ]
                    }
                ],
                "outputs": [
                    {
                        "type": "set",
                        "expression": "use( groovy.time.TimeCategory) { 10.seconds.from.now }",
                        "evaluate": true,
                        "to": [
                            "endDate"
                        ]
                    }
                ]
            },
            "input": [
                
            ],
            "output": [
                {
                    "name": "endDate",
                    "type": "date",
                    "defaultValue": "now"
                }
            ],
            "transitions": [
                {
                    "comments": "This transition uses a wait expression so that it will not transition to the next state until the current date time is equal or after the endDate.",
                    "lines": [
                        {
                            "from": [
                                "endDate"
                            ],
                            "to": [
                                "endDate"
                            ]
                        }
                    ],
                    "wait": "date:${ endDate }",
                    "to": "Print_End_Date"
                }
            ]
        },
        {
            "name": "Print_End_Date",
            "displayName": "Print End Date",
            "x": 120,
            "y": 200,
            "action": {
                "type": "invokeCode",
                "className": "io.toro.martini.LoggerMethods",
                "methodName": "info",
                "parameters": [
                    "String"
                ],
                "inputs": [
                    {
                        "type": "set",
                        "expression": "\"After 10 seconds, it finally transitions to Print End Date. End date was ${endDate}.\"",
                        "evaluate": true,
                        "to": [
                            "message"
                        ]
                    }
                ]
            },
            "input": [
                {
                    "name": "endDate",
                    "type": "date"
                }
            ],
            "output": [
                
            ]
        }
    ]
}