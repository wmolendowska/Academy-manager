    //
    // $(" .delete").on("click", function (event) {
    //     event.preventDefault();
    //     let href = $(this).attr('href');
    //     $('#deleteModal #deleteBtn').attr('href', href);
    //     $('#deleteModal').modal();
    // });


    // $('document').ready(function() {

        // $('.table .btn-warning').on('click',function(event){
        //
        //     event.preventDefault();
        //
        //     var href= $(this).attr('href');

        //     $.get(href, function(student, status){
        //         $('#IdEdit').val(student.id);
        //         $('#nameEdit').val(student.name);
        //         $('#departmentEdit').val(student.department);
        //         $('#updatedByEdit').val(student.updatedBy);
        //         $('#updatedOnEdit').val(student.updatedOn.substr(0,10));
        //     });
        //
        //     $('#editModal').modal();
        //
        // });

    $('#deleteModal').on('show.bs.modal', function (e) {
        $(this).find('#deleteButton').attr('href', $(e.relatedTarget).data('href'));
        $('#modal-deleteHiddenId').val($(this).find('#deleteButton').attr('href'));
    });

        // $('#deleteButton').on('click',function(event) {
        //     event.preventDefault();
        //     var href = $(this).attr('href');
        //     $('#deleteModal #delRef').attr('href', href);
        //     $('#deleteModal').style.display = "block";
        //
        // });

    // });

