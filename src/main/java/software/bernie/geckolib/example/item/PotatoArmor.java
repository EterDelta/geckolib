package software.bernie.geckolib.example.item;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.model.AnimatedArmorModel;
import software.bernie.geckolib.entity.IAnimatable;
import software.bernie.geckolib.event.predicate.SpecialAnimationPredicate;
import software.bernie.geckolib.example.client.renderer.model.armor.PotatoArmorModel;
import software.bernie.geckolib.item.armor.AnimatedArmorItem;
import software.bernie.geckolib.manager.AnimationManager;
import software.bernie.geckolib.block.SpecialAnimationController;

import javax.annotation.Nullable;

public class PotatoArmor extends AnimatedArmorItem implements IAnimatable
{
	private final AnimatedArmorModel model;
	private final AnimationManager manager = new AnimationManager();
	private final SpecialAnimationController controller = new SpecialAnimationController(this, "controller", 20, this::predicate);

	private <E extends IAnimatable> boolean predicate(SpecialAnimationPredicate<E> eSpecialAnimationPredicate)
	{
		controller.setAnimation(new AnimationBuilder().addAnimation("animation.potato_armor.new", true));
		return true;
	}

	public PotatoArmor(EquipmentSlotType slot)
	{
		super(ArmorMaterial.LEATHER, slot, new Item.Properties());
		this.model = new PotatoArmorModel();
		this.manager.addAnimationController(controller);
	}

	@Override
	public AnimatedArmorModel getModel()
	{
		return model;
	}

	@Override
	public AnimationManager getAnimationManager()
	{
		return manager;
	}

	@Nullable
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
	{
		model.setLivingAnimations(this);
		return (A) model.applySlot(armorSlot);
	}

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
	{
		return new ResourceLocation(GeckoLib.ModID, "textures/item/potato_armor.png").toString();
	}


}
