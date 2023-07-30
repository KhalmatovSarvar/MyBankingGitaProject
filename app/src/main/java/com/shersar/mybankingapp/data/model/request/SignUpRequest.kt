package com.shersar.mybankingapp.data.model.request

data class SignUpRequest(
  val firstName:String,
  val lastName:String,
  val dateOfBirth:String,
  val phoneNumber:String,
  val password:String,
  val gender:Int,
)