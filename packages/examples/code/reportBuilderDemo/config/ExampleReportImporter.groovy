package reportBuilderDemo.config

import io.toro.martini.LoggerMethods
import io.toro.martini.report.ReportService

class ExampleReportImporter {

    private ReportService reportService = 'reportService'.bean()

    public void importExampleReport() {
        try {
            if ( !getReport().isPresent() ) {
                InputStream reportFile = "resources/report-builder-demo.report".resourceStream( "examples" )
                reportService.importReport( reportFile )
            }
        } catch ( Exception ex ) {
            LoggerMethods.error("Error occured while trying to import example report: ${ex.message}")
        }
    }

    public void deleteExampleReport() {
        def report = getReport()
        if( report.isPresent() )
            reportService.deleteReportById( report.get().id() )
    }

    private def getReport() {
        reportService.findReportByPath( 'report-builder-demo' )
    }

}
