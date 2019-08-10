package com.example.desafioconcentresolutions.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioconcentresolutions.models.GitHubRepo
import com.example.desafioconcentresolutions.models.Resource
import com.example.desafioconcentresolutions.service.GitHubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(application:Application): AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private var mCurrentPageNumber = 1

    private val mGithubRepoList = MutableLiveData<Resource<List<GitHubRepo>>>()
    private val mGitHubService = GitHubService()

    fun getGitHubRepoList() = mGithubRepoList

    fun loadFirstPage(){
        if(mCurrentPageNumber == 1){
            mGithubRepoList.value = Resource.loading()
            ioScope.launch {
                val list = mGitHubService.listAllRepoByPage(1)
                mGithubRepoList.postValue(Resource.success(list))
            }
        }
    }
//
//    fun loadNextPage(){
//
//    }


}