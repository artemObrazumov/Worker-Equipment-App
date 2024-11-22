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
                    imageUrl = "https://sun9-1.userapi.com/impg/qVwmVZL-TNUcEVzRHUHVTaTcQSMlvr2b7UKG9A/_BKuikk8ngE.jpg?size=343x157&quality=95&sign=66558f63eb7118ab850a07545c71be1e&type=album",
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
                    imageUrl = "https://sun9-16.userapi.com/impg/m1FtE45hMgJ3vlEnLXb-aRIibI_-BXdKGVUR5A/1dUGJrcomto.jpg?size=438x267&quality=95&sign=a027a3641aea65eec1433f7d00a4a6cd&type=album",
                    name = "Подъёмные агрегаты",
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