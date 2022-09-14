package com.example.employeesregister

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

    @RequiresApi(Build.VERSION_CODES.O)
    fun main() {
        val employees = Employee(id = 1, fullName = "Marco Antonio Orozco Vasquez",
            academicLevel = AcademicLevel.BACHELOR, curp = "OOVM980201HOCRSR09",
            dateOfAdmission = LocalDate.of(2010, 8,5), budgetKey = "K1");


        val permission = Permission(employees, LocalDate.of(2022, 2,8), "Asunto familiar")

        val period = Period(initialDate = LocalDate.of(2022,2,1), finalDate = LocalDate.of(2022,2,15), description = "inicio de quincena")
        val schedule = Schedule.Builder(employees, period)
            .addOne(Schedule.Detail(DayOfWeek.MONDAY, LocalTime.of(8,20), LocalTime.of(6,10)))
            .addOne(Schedule.Detail(DayOfWeek.TUESDAY, LocalTime.of(8,0), LocalTime.of(6,10)))
            .addOne(Schedule.Detail(DayOfWeek.WEDNESDAY, LocalTime.of(8,30), LocalTime.of(6,10)))
            .build()
        val check = CheckInOut(LocalDate.now(), employees, LocalTime.of(9,30), LocalTime.of(6,30))

        val listPermissions: List<Permission> = listOf(permission)
        val incident = Incident(employee = employees, currentSchedule = schedule, checksInOut = listOf(check), permissions = listPermissions,
            initialDate = LocalDate.of(2022,2,1), finalDate = LocalDate.of(2022,2,10))

        check.isRetardant(schedule, listOf(permission))
        check.isAbsenceForIncorrectRegistration(schedule, listOf(permission))
        println("Employee report of ${employees.fullName} has ${incident.getAbsences()} absences");
    }