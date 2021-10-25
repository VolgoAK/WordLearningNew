package xyz.volgoak.data.training

interface TrainingRepository {
    suspend fun createTraining(type: TrainingType): Training
}