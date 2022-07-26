folder('Tools') {
    description('Folder for miscellaneous tools.')
}

job('Tools/clone-repository') {
    wrappers {
        preBuildCleanup {
            cleanupParameter('CLEANUP')
        }
    }
    parameters {
        stringParam("GIT_REPOSITORY_URL", "", "Git URL of the repository to clone")
    }
    scm {
        git {
            remote {
                url('\$GIT_REPOSITORY_URL')
            }
        }
    }
}

job('Tools/SEED'){
    parameters {
        stringParam("GITHUB_NAME", "", 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine")')
        stringParam("DISPLAY_NAME", "", "Display name for the job")
    }
    steps {
        dsl {
            text('job ("\$DISPLAY_NAME") {
                wrappers {
                    preBuildCleanup {
                        cleanupParameter(\'CLEANUP\')
                    }
                }
                scm {
                    git {
                        remote {
                            url(\"\$GITHUB_NAME\")
                        }
                    }\n\t
                }\n
            }')
        }
    }
}