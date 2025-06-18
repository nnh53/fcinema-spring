package com.react05.fcinema_spring.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1000, "Lỗi chưa được phân loại", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "Tên người dùng đã tồn tại",HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002, "Tên người dùng phải chứa ít nhất {min} kí tự ",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Mật khẩu phải chứa ít nhất {min} kí tự và nhiều nhất {max} kí tự",HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1004,"Email phải đúng dạng @gmail.com", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1005, "Khóa không hợp lệ", HttpStatus.BAD_REQUEST),
    NOT_NULL(1006, "Trường không được để trống", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007, "Email hoặc mật khẩu không tồn tại", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1008, "Không tìm thấy người dùng", HttpStatus.NOT_FOUND),
    PROMOTION_NOT_FOUND(1009, "Không tìm thấy khuyến mãi", HttpStatus.NOT_FOUND),
    PROMOTION_EXISTED(1010, "Khuyến mãi đã tồn tại", HttpStatus.BAD_REQUEST),
    ROOM_NOT_FOUND(1011, "Không tìm thấy phòng chiếu", HttpStatus.NOT_FOUND),
    SEAT_NOT_FOUND(1012, "Không tìm thấy ghế", HttpStatus.NOT_FOUND),
    INVALID_SEAT_CAPACITY(1013, "Số lượng ghế có thể sử dụng không khớp với sức chứa phòng", HttpStatus.BAD_REQUEST);
    int code;
    String message;
    HttpStatusCode httpStatusCode;
}