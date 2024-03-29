package dev.rlnt.lazierae2.container;

import dev.rlnt.lazierae2.container.base.ProcessorContainer;
import dev.rlnt.lazierae2.inventory.base.MultiItemHandler;
import dev.rlnt.lazierae2.inventory.component.MultiInputSlot;
import dev.rlnt.lazierae2.setup.ModConfig;
import dev.rlnt.lazierae2.setup.ModContainers;
import dev.rlnt.lazierae2.tile.EtcherTile;
import dev.rlnt.lazierae2.tile.base.ProcessorTile;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class EtcherContainer extends ProcessorContainer<EtcherTile> {

    public EtcherContainer(int windowID, PlayerInventory playerInventory, EtcherTile tile, IIntArray info) {
        super(ModContainers.ETCHER.get(), windowID, playerInventory, tile, info);
    }

    public EtcherContainer(int windowID, PlayerInventory playerInventory, EtcherTile tile) {
        this(windowID, playerInventory, tile, new IntArray(ProcessorTile.INFO_SIZE));
    }

    @Override
    protected void initContainerInventory() {
        // create upgrade and output slot first
        super.initContainerInventory();

        MultiItemHandler itemHandler = tile.getItemHandler();

        // input slots
        addSlot(new MultiInputSlot(itemHandler, tile.getInputSlots()[0], 37, 17, this));
        addSlot(new MultiInputSlot(itemHandler, tile.getInputSlots()[1], 60, 35, this));
        addSlot(new MultiInputSlot(itemHandler, tile.getInputSlots()[2], 37, 53, this));
    }

    @Override
    public int getEnergyCapacityAdditional() {
        return getEnergyCapacity() - ModConfig.PROCESSING.etcherEnergyBuffer.get();
    }

    @Override
    public float getEnergyConsumptionMultiplier() {
        return (float) getEffectiveEnergyConsumption() / (float) ModConfig.PROCESSING.etcherEnergyCostBase.get();
    }

    @Override
    public double getProcessTimeMultiplier(int upgradeAmount) {
        return Math.pow(ModConfig.PROCESSING.etcherWorkTicksUpgrade.get(), upgradeAmount);
    }
}
