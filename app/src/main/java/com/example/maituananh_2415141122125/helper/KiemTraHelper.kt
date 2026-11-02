package com.example.maituananh_2415141122125.helper

object KiemTraHelper {
    fun kiemTraHoTen(hoTen: String): Boolean {
        val regex = Regex("^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵýỷỹ]+(?: [a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵýỷỹ]+)+$")
        return regex.matches(hoTen)
    }

    fun kiemTraSdt(sdt: String): Boolean {
        val regex = Regex("^(05|09)\\d{8}$")
        return regex.matches(sdt)
    }

    fun kiemTraEmail(email: String): Boolean {
        val regex = Regex("^[a-zA-Z][a-zA-Z0-9._]{2,29}@ute\\.udn\\.vn$")
        return regex.matches(email)
    }

    fun kiemTraNgay(ngay: String): Boolean {
        val regex = Regex("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(202[6-9])$")
        return regex.matches(ngay)
    }
}
