package com.example.valxguide.ui.main.weapons.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.valxguide.data.model.DamageRange
import com.example.valxguide.data.model.Weapon
import com.example.valxguide.databinding.BackItemBinding
import com.example.valxguide.databinding.WeaponDetailDamageItemBinding
import com.example.valxguide.databinding.WeaponDetailDescItemBinding
import com.example.valxguide.databinding.WeaponDetailIconItemBinding
import com.example.valxguide.databinding.WeaponDetailSkinsItemBinding
import com.squareup.picasso.Picasso

class WeaponDetailAdapter(
    private val repository: List<Pair<Int, Any>>,
    private val onClickBack: () -> Unit
) : RecyclerView.Adapter<WeaponDetailAdapter.WeaponDetailHolder>() {

    abstract class WeaponDetailHolder(item: View) : ViewHolder(item) {
        abstract fun bind(data: Any)
    }

    inner class BackItemHolder(binding: BackItemBinding) : WeaponDetailHolder(binding.root) {
        private val tvBackTittle = binding.tvBackTittle
        private val btnBackIcon = binding.ivBackIcon

        override fun bind(data: Any) {
            val tittle = data as? String
            tittle?.let { tvBackTittle.text = it }
            btnBackIcon.setOnClickListener { onClickBack() }
        }
    }

    inner class WeaponDetailIconHolder(binding: WeaponDetailIconItemBinding) : WeaponDetailHolder(binding.root) {
        private val ivWeaponIcon = binding.ivWeaponDetailIcon

        override fun bind(data: Any) {
            val weaponIconUrl = data as? String
            weaponIconUrl?.let { Picasso.get().load(it).into(ivWeaponIcon) }
        }
    }

    inner class WeaponDetailDescHolder(binding: WeaponDetailDescItemBinding) : WeaponDetailHolder(binding.root) {
        private val gdvWeaponType = binding.gdvWeaponType
        private val gdvWeaponCreds = binding.gdvWeaponCreds
        private val gdvWeaponMagazine = binding.gdvWeaponMagazine
        private val gdvWeaponWallPenetration = binding.gdvWeaponWallPenetration
        private val gdvWeaponReloadTime = binding.gdvWeaponReloadTime
        private val gdvWeaponEquipTime = binding.gdvWeaponEquipTime
        private val gdvWeaponFireRate = binding.gdvWeaponFireRate

        override fun bind(data: Any) {
            val weapon = data as? Weapon

            if(weapon != null) {
                weapon.weaponStats?.let {
                    gdvWeaponMagazine.setContentText(it.magazineSize.toString())
                    gdvWeaponWallPenetration.setContentText(it.wallPenetration.split("::")[1])
                    gdvWeaponReloadTime.setContentText(it.reloadTimeSeconds.toString())
                    gdvWeaponEquipTime.setContentText(it.equipTimeSeconds.toString())
                    gdvWeaponFireRate.setContentText(it.fireRate.toInt().toString())
                }

                weapon.shopData?.let {
                    gdvWeaponType.setContentText(it.categoryText)
                    gdvWeaponCreds.setContentText(it.cost.toString())
                }
            }
        }
    }

    inner class WeaponDetailDamageHolder(binding: WeaponDetailDamageItemBinding) : WeaponDetailHolder(binding.root) {
        private val tvRange1 = binding.tvColumn1RangeDesc1
        private val tvBody1 = binding.tvColumn2BodyDesc1
        private val tvHead1 = binding.tvColumn3HeadDesc1
        private val tvLeg1 = binding.tvColumn4LegDesc1

        private val tvRange2 = binding.tvColumn1RangeDesc2
        private val tvBody2 = binding.tvColumn2BodyDesc2
        private val tvHead2 = binding.tvColumn3HeadDesc2
        private val tvLeg2 = binding.tvColumn4LegDesc2

        override fun bind(data: Any) {
            val damageRanges = data as? List<DamageRange>

            damageRanges?.let { damageRanges ->

                val damageRange1 = damageRanges[0]

                tvRange1.text = buildString {
                    append(damageRange1.rangeStartMeters)
                    append(" - ")
                    append(damageRange1.rangeEndMeters)
                }
                tvBody1.text = damageRange1.bodyDamage.toString()
                tvHead1.text = damageRange1.headDamage.toInt().toString()
                tvLeg1.text = damageRange1.legDamage.toInt().toString()

                if(damageRanges.size == 2) {
                    val damageRange2 = damageRanges[1]

                    tvRange2.text = buildString {
                        append(damageRange2.rangeStartMeters)
                        append(" - ")
                        append(damageRange2.rangeEndMeters)
                    }
                    tvBody2.text = damageRange2.bodyDamage.toString()
                    tvHead2.text = damageRange2.headDamage.toInt().toString()
                    tvLeg2.text = damageRange2.legDamage.toInt().toString()
                } else {
                    tvRange2.hide()
                    tvBody2.hide()
                    tvHead2.hide()
                    tvLeg2.hide()
                }
            }
        }
    }

    inner class WeaponDetailSkinsHolder(binding: WeaponDetailSkinsItemBinding) : WeaponDetailHolder(binding.root) {
        private val rvWeaponSkins = binding.rvWeaponDetailSkins

        override fun bind(data: Any) {
            val skinList = data as? List<String>
            skinList?.let { rvWeaponSkins.adapter = WeaponDetailSkinsAdapter(it) }
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, viewType: Int): WeaponDetailHolder {

        val inflater = LayoutInflater.from(group.context)

        return when (viewType) {
            0 -> BackItemHolder(BackItemBinding.inflate(inflater, group, false))
            1 -> WeaponDetailIconHolder(WeaponDetailIconItemBinding.inflate(inflater, group, false))
            2 -> WeaponDetailDescHolder(WeaponDetailDescItemBinding.inflate(inflater, group, false))
            3 -> WeaponDetailDamageHolder(WeaponDetailDamageItemBinding.inflate(inflater, group, false))
            4 -> WeaponDetailSkinsHolder(WeaponDetailSkinsItemBinding.inflate(inflater, group, false))
            else -> throw IllegalArgumentException("Illegal type for holder")
        }
        // 0 -> BackItem - String
        // 1 -> WeaponIcon - String
        // 2 -> Desc - Weapon
        // 3 -> Damage - Pair<DamageRange, DamageRange>
    }

    override fun getItemCount(): Int = repository.size

    override fun getItemViewType(position: Int): Int = repository[position].first

    override fun onBindViewHolder(holder: WeaponDetailHolder, position: Int) {
        holder.bind(repository[position].second)
    }
}

private fun View.hide() {
    this.visibility = View.GONE
}