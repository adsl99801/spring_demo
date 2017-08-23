package com.lfo.models


import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "student")
class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @NotNull
    var name: String = ""
}