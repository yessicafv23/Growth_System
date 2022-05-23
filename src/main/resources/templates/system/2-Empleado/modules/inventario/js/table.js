$(document).ready(function () {
    $('#myTable').DataTable({
        responsive: true,
        scrollY: "380px",
        scrollCollapse: true,
        paging: false,
        dom: 'Bfrtilp',
        buttons: [{
                extend: 'collection',
                text: 'Descargar',
                className: 'btn btn-primary',
                buttons: [{
                        extend: 'excelHtml5',
                        text: '<i class="bi bi-file-earmark-spreadsheet-fill"></i> EXCEL',
                        titleAttr: 'Exportar a Excel',
                        className: 'btn btn-success mt-1 border-dark border-3'
                    },
                    {
                        extend: 'csv',
                        text: '<i class="bi bi-file-earmark-spreadsheet-fill"></i>CSV',
                        titleAttr: 'Exporta csv',
                        className: 'btn btn-success mt-1 border-dark border-3'
                    },
                    {
                        extend: 'pdfHtml5',
                        text: '<i class="bi bi-file-earmark-pdf-fill"></i> PDF',
                        titleAttr: 'Exportar a pdf',
                        className: 'btn btn-danger mt-1 border-dark border-3'
                    }
                ]
            },
            {
                extend: 'print',
                text: '<i class="bi bi-printer-fill"></i> IMPRIMIR',
                titleAttr: 'Exportar a print',
                className: 'btn btn-info'
            }
        ]
    });
});