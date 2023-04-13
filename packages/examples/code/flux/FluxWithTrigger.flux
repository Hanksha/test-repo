{
    "comment": "# [Invoking Flux Using Triggers](https://docs.torocloud.com/martini/latest/quick-start/resources/examples-package/flux/)\n\nThis [Flux service](https://docs.torocloud.com/martini/developing/services/flux/) demonstrates the use of [triggers](https://docs.torocloud.com/martini/developing/services/flux/triggering/).\n\nTriggers are events that can start a Flux service. When the Flux engine receives an event, it will look for Flux services with matching trigger events and start them.\n\nThis Flux service has two triggers: `trigger1` and `trigger2`. We'll be starting this service by sending these events. We'll use log messages to verify if the service has been executed.\n\n## Invoking the Service\n\nTo run this service, [send an event with the same name as the configured triggers, `trigger1` or `trigger2`](https://docs.torocloud.com/martini/developing/services/flux/triggering/#triggering-flux-services).\n\n## Expected Output\n\nIf triggered properly, the [Martini logs](https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view), will display something like:\n\n```\nINFO  [Martini] This Flux service was started using the trigger \"<trigger>\". If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\n```\n\nIf the service was invoked without a trigger instead of sending a Flux event:\n\n```\nINFO  [Martini] This Flux should be triggered by an event. If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\n```\n",
    "triggers": [
        "trigger2",
        "trigger1"
    ],
    "input": [
        {
            "name": "$trackerId",
            "comments": "Used for tracking the flux service"
        }
    ],
    "states": [
        {
            "name": "Print_Trigger",
            "displayName": "Print Trigger",
            "x": 120,
            "y": 120,
            "comment": "This states logs the event data that triggered this Flux service. The triggering event can be retrieved using the special property '$triggerFluxEvent'.",
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
                        "expression": "if ( $context.hasProperty( '$triggerFluxEvent' ) )\n    \"This Flux service was started using the trigger \\\"${$triggerFluxEvent}\\\".  If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\"\nelse\n    \"This Flux should be triggered by an event.  If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\"",
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