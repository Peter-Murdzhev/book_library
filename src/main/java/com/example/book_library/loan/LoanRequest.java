package com.example.book_library.loan;

import jakarta.validation.constraints.NotNull;

public record LoanRequest(@NotNull Integer bookId,@NotNull Integer readerId) {
}
