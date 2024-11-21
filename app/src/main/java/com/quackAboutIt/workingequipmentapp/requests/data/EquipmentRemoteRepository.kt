package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.requests.domain.Equipment
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentResult
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentType

class EquipmentRemoteRepository: EquipmentRepository {

    override suspend fun getEquipment(token: String): EquipmentResult {
        return EquipmentResult.Success(
            listOf(
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test",
                    types = listOf(
                        EquipmentType(
                            id = 1,
                            name = "1"
                        ),
                        EquipmentType(
                            id = 2,
                            name = "2"
                        )
                    )
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test",
                    types = listOf(
                        EquipmentType(
                            id = 1,
                            name = "1"
                        ),
                        EquipmentType(
                            id = 2,
                            name = "2"
                        )
                    )
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test",
                    types = listOf(
                        EquipmentType(
                            id = 1,
                            name = "1"
                        ),
                        EquipmentType(
                            id = 2,
                            name = "2"
                        )
                    )
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test",
                    types = listOf(
                        EquipmentType(
                            id = 1,
                            name = "1"
                        ),
                        EquipmentType(
                            id = 2,
                            name = "2"
                        )
                    )
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test",
                    types = listOf(
                        EquipmentType(
                            id = 1,
                            name = "1"
                        ),
                        EquipmentType(
                            id = 2,
                            name = "2"
                        )
                    )
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test",
                    types = listOf(
                        EquipmentType(
                            id = 1,
                            name = "1"
                        ),
                        EquipmentType(
                            id = 2,
                            name = "2"
                        )
                    )
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test",
                    types = listOf(
                        EquipmentType(
                            id = 1,
                            name = "1"
                        ),
                        EquipmentType(
                            id = 2,
                            name = "2"
                        )
                    )
                ),
            )
        )
    }
}