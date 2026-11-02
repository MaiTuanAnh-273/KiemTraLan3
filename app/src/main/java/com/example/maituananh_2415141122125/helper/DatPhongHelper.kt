package com.example.maituananh_2415141122125.helper

import com.example.maituananh_2415141122125.data.DuLieuMau
import com.example.maituananh_2415141122125.model.LoaiPhong

object DatPhongHelper {
    fun timLoaiPhong(ma: String): LoaiPhong? {
        val maTim = ma.trim().uppercase()
        return DuLieuMau.dsLoaiPhong.find { it.ma.trim().uppercase() == maTim }
    }

    fun hopLeSoKhach(loai: LoaiPhong, soKhach: Int): Boolean {
        return soKhach in 1..(loai.sucChua + 2)
    }

    fun tinhTienPhong_2415141122125(loai: LoaiPhong, soDem: Int, soKhach: Int): Long {
        if (soDem < 1 || !hopLeSoKhach(loai, soKhach)) {
            return 0
        }

        var tienPhong = loai.giaMotDem * soDem

        if (soDem >= 5) {
            tienPhong = (tienPhong * 85) / 100
        }

        val soKhachVuot = maxOf(0, soKhach - loai.sucChua)
        val phuThu = soKhachVuot * 150000L * soDem

        return tienPhong + phuThu
    }
}
