package com.example.maituananh_2415141122125

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.maituananh_2415141122125.data.DuLieuMau
import com.example.maituananh_2415141122125.helper.DatPhongHelper
import com.example.maituananh_2415141122125.helper.KiemTraHelper
import com.example.maituananh_2415141122125.model.LoaiPhong
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.widget.TextView

class DatPhongActivity : AppCompatActivity() {
    private lateinit var tilHoTen: TextInputLayout
    private lateinit var tilSdt: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilNgayNhan: TextInputLayout
    private lateinit var tilSoDem: TextInputLayout
    private lateinit var tilSoKhach: TextInputLayout

    private lateinit var edtHoTen: TextInputEditText
    private lateinit var edtSdt: TextInputEditText
    private lateinit var edtEmail: TextInputEditText
    private lateinit var edtNgayNhan: TextInputEditText
    private lateinit var edtSoDem: TextInputEditText
    private lateinit var edtSoKhach: TextInputEditText

    private lateinit var txtLoaiPhong: TextView
    private lateinit var txtTamTinh: TextView
    private lateinit var btnDatPhong: MaterialButton

    private var loaiPhongHienTai: LoaiPhong = DuLieuMau.dsLoaiPhong[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dat_phong)

        khoiTaoView()
        capNhatLoaiPhong()
        xuLySuKien()
    }

    private fun khoiTaoView() {
        tilHoTen = findViewById(R.id.tilHoTen)
        tilSdt = findViewById(R.id.tilSdt)
        tilEmail = findViewById(R.id.tilEmail)
        tilNgayNhan = findViewById(R.id.tilNgayNhan)
        tilSoDem = findViewById(R.id.tilSoDem)
        tilSoKhach = findViewById(R.id.tilSoKhach)

        edtHoTen = findViewById(R.id.edtHoTen)
        edtSdt = findViewById(R.id.edtSdt)
        edtEmail = findViewById(R.id.edtEmail)
        edtNgayNhan = findViewById(R.id.edtNgayNhan)
        edtSoDem = findViewById(R.id.edtSoDem)
        edtSoKhach = findViewById(R.id.edtSoKhach)

        txtLoaiPhong = findViewById(R.id.txtLoaiPhong)
        txtTamTinh = findViewById(R.id.txtTamTinh)
        btnDatPhong = findViewById(R.id.btnDatPhong)
    }

    private fun capNhatLoaiPhong() {
        txtLoaiPhong.text = "Loại phòng: ${loaiPhongHienTai.ten} - ${loaiPhongHienTai.giaMotDem} đ/đêm"
    }

    private fun xuLySuKien() {
        edtSoDem.doAfterTextChanged { capNhatTamTinh() }
        edtSoKhach.doAfterTextChanged { capNhatTamTinh() }

        btnDatPhong.setOnClickListener {
            if (kiemTraDuLieu()) {
                val hoTen = edtHoTen.text.toString().trim()
                Toast.makeText(this, "Đã ghi nhận đơn đặt phòng của $hoTen", Toast.LENGTH_SHORT).show()
            } else {
                Snackbar.make(it, "Vui lòng kiểm tra lại thông tin", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun capNhatTamTinh() {
        val soDem = edtSoDem.text.toString().toIntOrNull() ?: 0
        val soKhach = edtSoKhach.text.toString().toIntOrNull() ?: 0

        val tienTamTinh = DatPhongHelper.tinhTienPhong_2415141122125(loaiPhongHienTai, soDem, soKhach)
        txtTamTinh.text = "Tạm tính: $tienTamTinh"
    }

    private fun kiemTraDuLieu(): Boolean {
        var hopLe = true

        val hoTen = edtHoTen.text.toString().trim()
        val sdt = edtSdt.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val ngayNhan = edtNgayNhan.text.toString().trim()
        val soDem = edtSoDem.text.toString().trim()
        val soKhach = edtSoKhach.text.toString().trim()

        if (!KiemTraHelper.kiemTraHoTen(hoTen)) {
            tilHoTen.error = "Họ tên không hợp lệ (ít nhất 2 từ, chỉ chữ cái)"
            hopLe = false
        } else {
            tilHoTen.error = null
        }

        if (!KiemTraHelper.kiemTraSdt(sdt)) {
            tilSdt.error = "Số điện thoại phải 10 số, bắt đầu 05 hoặc 09"
            hopLe = false
        } else {
            tilSdt.error = null
        }
        if (!KiemTraHelper.kiemTraEmail(email)) {
            tilEmail.error = "Email không hợp lệ (phải có @ute.udn.vn)"
            hopLe = false
        } else {
            tilEmail.error = null
        }

        if (!KiemTraHelper.kiemTraNgay(ngayNhan)) {
            tilNgayNhan.error = "Ngày không hợp lệ (dd/MM/yyyy, năm 2026-2029)"
            hopLe = false
        } else {
            tilNgayNhan.error = null
        }

        val soDemInt = soDem.toIntOrNull()
        if (soDemInt == null || soDemInt < 1 || soDemInt > 15) {
            tilSoDem.error = "Số đêm phải từ 1 đến 15"
            hopLe = false
        } else {
            tilSoDem.error = null
        }

        val soKhachInt = soKhach.toIntOrNull()
        if (soKhachInt == null || !DatPhongHelper.hopLeSoKhach(loaiPhongHienTai, soKhachInt)) {
            tilSoKhach.error = "Số khách không hợp lệ (1 đến ${loaiPhongHienTai.sucChua + 2})"
            hopLe = false
        } else {
            tilSoKhach.error = null
        }

        return hopLe
    }
}
