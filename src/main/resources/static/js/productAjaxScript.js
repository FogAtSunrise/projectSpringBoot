

$(document).ready(function () {

    //Форма добавления изделия
    let $productForm = $("#productForm");

//Форма ввода для поиска


    let $productFindInput = $("#findProductInput");


    //Отправка формы добавления изделия
    $productForm.on('submit', function (e) {
        e.preventDefault();
        let formData = new FormData(this);
        let photo = $("#photo");
        formData.append("photo", photo.prop('files')[0])
        $.ajax({
            type: 'POST',
            url: $productForm.attr('action'),
            enctype: "multipart/form-data",
            data: formData,
            processData: false,
            contentType: false,
        }).done(function (response) {
            $("#modalAddProduct").hide();
            showNewProduct(response);
        });
    });

    //Удалить изделия
    $(document).on('click', '.btn-product-delete', function () {
        let id = $(this).attr("id");
        $.ajax({
            type: 'GET',
            url: "/delete_product_from_page/" + id
        });
    });


    //Скрыть все
    function hideProductCards() {
        $(".product_card").each(function () {
            $(this).hide();
        });
    }



       //Поиск
        $productFindInput.on('input', function (e) {
            e.preventDefault();
            let inputData = JSON.stringify({
                "text" : $productFindInput.val()
            });
            $.ajax({
                type: 'POST',
                url: '/products/find',
                data: inputData,
                processData: false,
                contentType: 'application/json',
                dataType: "json"
            }).done(function (response) {
                hideProductCards();
                for (let i = 0; i < response.length; i++) {
                    $("#product_" + response[i].id).show();
                }
            });
        });


    //Отобразить
    function showNewProduct(product) {
            let $newCard = $('<div class="col-6 col-md-5 col-lg-4 col-xl-3 product_card">' +
                '<div class="card shadow-sm">' +
                '<img src="" alt="..." class="img-fluid">' +
                '<div class="card-body">' +
                '<p class="card-text"></p>' +
                '<div class="d-flex justify-content-between align-items-center">' +
                '<a href="">' +
                '<div class="btn-group">\n' +
                '<button type="button" class="btn btn-dark btn-outline-info">Подробнее</button>\n' +
                '</div>' +
                '</a>' +
                '<div class="btn-group">' +
                '<button type="button" class="btn-product-delete btn btn-dark btn-outline-danger" id="">Удалить</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>'
            )
            $("img", $newCard).attr('src', product.photo);
            $("p", $newCard).text(product.name);
            $("a", $newCard).attr('href', "products/" + product.id);
            $(".btn-product-delete", $newCard).attr('id', product.id);
            $($newCard).attr('id', "product_" + product.id);
            $('#add-card').before($newCard);
        }
});
