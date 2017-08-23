package com.lfo.demo

import com.google.gson.Gson
import com.lfo.models.Student
import org.hibernate.Session
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class Home {

    @Bean
    fun openSession(): Session {
        val registry = StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build()
        var sessionFactory = MetadataSources(registry).buildMetadata().buildSessionFactory()

        var session = sessionFactory.openSession()
        return session
    }

    @Autowired
    lateinit var session: Session

    @RequestMapping("/")
    @ResponseBody
    fun home(): String {
        return "Hello World!"
    }

    @RequestMapping("/all")
    @ResponseBody
    fun all(): String {


        var result = session.createQuery("from Student").list() as List<Student>
        val gson = Gson()
        return gson.toJson(result)
    }

    @RequestMapping("/add")
    @ResponseBody
    fun add(): String {
        session.beginTransaction()
        var vo = Student()
        vo.name = "newName"
        session.save(vo)
        session.transaction.commit()
        return "added!"
    }

    @RequestMapping("/removeAll")
    @ResponseBody
    fun removeAll(): String {
        session.beginTransaction()
        val hql = String.format("delete from %s", "Student")
        val query = session.createQuery(hql)
        query.executeUpdate()
        session.transaction.commit()
        return "removeAll!"
    }
}