/*******************************************************************************
 * Copyright 2017 jamietech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package ch.jamiete.hilda.admin.commands;

import ch.jamiete.hilda.Hilda;
import ch.jamiete.hilda.admin.AdminPlugin;
import ch.jamiete.hilda.commands.ChannelSeniorCommand;
import ch.jamiete.hilda.commands.ChannelSubCommand;
import net.dv8tion.jda.core.entities.Message;

public class AdminLoudCommand extends ChannelSubCommand {
    private AdminPlugin plugin;

    public AdminLoudCommand(final Hilda hilda, final ChannelSeniorCommand senior, final AdminPlugin plugin) {
        super(hilda, senior);

        this.plugin = plugin;

        this.setName("loud");
        this.setDescription("Broadcasts all log messages.");
    }

    @Override
    public void execute(final Message message, final String[] arguments, final String label) {
        plugin.getReporter().setLoud(!plugin.getReporter().getLoud());
        this.reply(message, plugin.getReporter().getLoud() ? "I will now broadcast all log messages." : "I will stop broadcasting log messages.");
    }

}
