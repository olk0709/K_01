package com.example.k_01.repository

class RepositoryImpl:Repository {
    override fun getWeatherFromServer():Weather {

        Thread.sleep(2000L)  //эмуляция запроса на сервер
        return Weather()
    }

   /* override fun getWeatherFromLocalStorage():Weather {
        //эмуляция локального запроса
        Thread.sleep(20L)
        return Weather()
    }*/

    override fun getWorldWeatherFromLocalStorage() =  getWorldCities() //эмуляция ответа
    }
    override fun getRussionWeatherFromLocalStorage() = getRussianCities()
    }
}