{
    "comment": "# [Using Flux Tags](https://docs.torocloud.com/martini/latest/quick-start/resources/examples-package/flux)\n\nThis [Flux service](https://docs.torocloud.com/martini/developing/services/flux/) demonstrates [waiting and the use of tags](https://docs.torocloud.com/martini/developing/services/flux/waiting/).\n\nTags allow for the selective sending of events. A Flux service with configured tags will only be notified of an event if it was sent with any of the service's configured tags.\n\nFlux service tags are configured via service properties. This service supports two tags: the static tag `fluxTag`, and a dynamic tag equal to the value of the `dynamicTag` [input property](https://docs.torocloud.com/martini/developing/services/overview/input-output/). This means this service will only respond to events sent with the tag `fluxTag` or the value of `dynamicTag` (which is `dynamicFluxTag` by default).\n\n## Invoking the Service\n\n[Run this Flux](https://docs.torocloud.com/martini/developing/services/flux/running/) as you normally would. Ensure the Start State is set to `Start_State`. You may provide a different value for `dynamicTag`, or you can leave it be with its default value `dynamicFluxTag`.\n\nUpon executing the first state, the service will transition to the second state. At this moment, it will wait for the `done` event. To continue, we must [send a `done` event](https://docs.torocloud.com/martini/developing/services/flux/waiting/#sending-an-external-event); but we must send it with the tag(s) `fluxTag` or `dynamicFluxTag` (or whatever value was set for `dynamicTag`).\n\nOnce sent, `End_State` will be executed and the service will terminate.\n\n## Expected Output\n\nUpon running this service, you should see a log message like below in the [Martini console](https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view):\n\n```\nINFO  [Martini] The flow starts at Start_State and then transitions to \"Wait for Event\" state. In this state, you need to manually send an event to the static tag \"fluxTag\" or to the value you set on \"dynamicTag\". To send an event, right-click anywhere in the Flux service editor and select Send Flux Event. After selecting Send Flux Event a pop up window will appear. In the event field, input \"done\" and then in the tags field, you can either put \"fluxTag\" or the value of your dynamicTag . To learn more sending an event to a waiting state in Flux, visit https://docs.torocloud.com/martini/developing/services/flux/waiting/\n```\n\nAfter sending the `done` event (provided that it has been sent with proper tags), the console will display another log message that looks like:\n\n```\nINFO  [Martini] We have reached End_State after sending a \"done\" event. If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\n```\n",
    "tags": [
        "${ dynamicTag }",
        "fluxTag"
    ],
    "input": [
        {
            "name": "dynamicTag",
            "defaultValue": "dynamicFluxTag",
            "comments": "This input property is used to add a tag dynamically to the Flux service."
        },
        {
            "name": "$trackerId",
            "comments": "Used for tracking the flux service"
        }
    ],
    "startState": "Start_State",
    "states": [
        {
            "name": "Wait_for_Event",
            "displayName": "Wait for Event",
            "x": 280,
            "y": 120,
            "comment": "This state waits for the event 'done'. Note that the event must be sent with a matching tag.",
            "waiting": true,
            "action": {
                
            },
            "input": [
                
            ],
            "output": [
                
            ],
            "transitions": [
                {
                    "event": "done",
                    "to": "End_State"
                }
            ]
        },
        {
            "name": "Start_State",
            "displayName": "Start State",
            "x": 120,
            "y": 120,
            "comment": "This state is the start state of the Flux service, it just logs the state name.",
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
                        "expression": "\"The flow starts at ${$fluxStateName} and then transitions to \\\"Wait for Event\\\" state. In this state, you need to manually send an event to the static tag \\\"fluxTag\\\" or to the value you set on \\\"dynamicTag\\\". To send an event, right-click anywhere in the Flux service editor and select Send Flux Event. After selecting Send Flux Event a pop up window will appear. In the event field, input \\\"done\\\" and then in the tags field, you can either put \\\"fluxTag\\\" or the value of your dynamicTag . To learn more sending an event to a waiting state in Flux, visit https://docs.torocloud.com/martini/latest/developing/services/flux/waiting/\"",
                        "evaluate": true,
                        "to": [
                            "message"
                        ]
                    }
                ]
            },
            "input": [
                
            ],
            "output": [
                
            ],
            "transitions": [
                {
                    "to": "Wait_for_Event"
                }
            ]
        },
        {
            "name": "End_State",
            "displayName": "End State",
            "x": 282,
            "y": 281,
            "comment": "This state is the end state of the Flux service, it just logs the state name.",
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
                        "expression": "\"We have reached ${$fluxStateName} after sending a \\\"done\\\" event. If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\"",
                        "evaluate": true,
                        "to": [
                            "message"
                        ]
                    }
                ]
            },
            "input": [
                
            ],
            "output": [
                
            ]
        }
    ]
}